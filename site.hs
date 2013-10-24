--------------------------------------------------------------------------------
{-# LANGUAGE OverloadedStrings #-}
import           Data.Monoid (mappend, (<>))
import           Hakyll
import Text.Pandoc
import qualified Data.Map as M


--------------------------------------------------------------------------------

config :: Configuration
config = defaultConfiguration 
    { deployCommand = "scp -r _site/* rain:~/public_html" 
    }

pandocOptions :: WriterOptions
pandocOptions = defaultHakyllWriterOptions{ writerHTMLMathMethod = MathJax "" }

mathCtx :: Context a
mathCtx = field "mathjax" $ \item -> do
    metadata <- getMetadata $ itemIdentifier item
    return $ if "mathjax" `M.member` metadata
                  then "<script type=\"text/javascript\" src=\"http://cdn.mathjax.org/mathjax/latest/MathJax.js?config=TeX-AMS-MML_HTMLorMML\"></script>"
                  else ""

main :: IO ()
main = hakyllWith config $ do
    match "images/*" $ do
        route   idRoute
        compile copyFileCompiler

    match "css/*" $ do
        route   idRoute
        compile compressCssCompiler

    match (fromList ["contact.markdown"]) $ do
        route   $ setExtension "html"
        compile $ pandocCompiler
            >>= loadAndApplyTemplate "templates/default.html" (mathCtx <> defaultContext)
            >>= relativizeUrls

    match "src/*" $ do
        route   idRoute
        compile copyFileCompiler

    match "pdf/*" $ do
        route   idRoute
        compile copyFileCompiler

    match "posts/*" $ do
        route $ setExtension "html"
        compile $ pandocCompilerWith defaultHakyllReaderOptions pandocOptions
            >>= loadAndApplyTemplate "templates/post.html"    (mathCtx <> postCtx)
            >>= loadAndApplyTemplate "templates/default.html" (mathCtx <> postCtx)
            >>= relativizeUrls

--    create ["archive.html"] $ do
--        route idRoute
--        compile $ do
--            posts <- recentFirst =<< loadAll "posts/*"
--            let archiveCtx =
--                    listField "posts" postCtx (return posts) `mappend`
--                    constField "title" "Архив"            `mappend`
--                    defaultContext
--
--            makeItem ""
--                >>= loadAndApplyTemplate "templates/archive.html" archiveCtx
--                >>= loadAndApplyTemplate "templates/default.html" archiveCtx
--                >>= relativizeUrls


    match "index.html" $ do
        route idRoute
        compile $ do
            posts <- recentFirst =<< loadAll "posts/*"
            let indexCtx =
                    listField "posts" postCtx (return posts) `mappend`
                    constField "title" "Home"                `mappend`
                    defaultContext

            getResourceBody
                >>= applyAsTemplate indexCtx
                >>= loadAndApplyTemplate "templates/default.html" (mathCtx <> indexCtx)
                >>= relativizeUrls

    match "templates/*" $ compile templateCompiler


--------------------------------------------------------------------------------
postCtx :: Context String
postCtx =
    dateField "date" "%B %e, %Y" `mappend`
    defaultContext
