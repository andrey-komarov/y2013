#/bin/bash
for p in problems
do
    latex $p.tex
    dvips $p.dvi
    ps2pdf $p.ps
    cp $p.pdf ../pdf
done
