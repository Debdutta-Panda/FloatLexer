package com.debduttapanda.floatlexer.scanners;
import java.io.StringReader;
import java.util.ArrayList;

%%

%class FloatScanner
%unicode
%standalone
%public
%{
    public ArrayList<Float> list = new ArrayList();

    public static ArrayList<Float> scan(String input){
                  StringReader sr = new StringReader(input);
                  FloatScanner lexer = new FloatScanner(sr);
                  return lexer._scan();
              }
              private ArrayList<Float> _scan(){
                try {
                  while(!yyatEOF()) yylex();
                } catch (java.io.IOException e) {
                  throw new RuntimeException(e);
                }
                try {
                  yyclose();
                } catch (Exception e) {
                      throw new RuntimeException(e);
                  }
                return list;
              }
%}


Zero            = [0]
AnyDigit        = [0-9]
NonZeroStarter  = [1-9]
Dot             = [\.]
NonZeroIntegral = {NonZeroStarter} {AnyDigit}*
Integer         = {Zero} | {NonZeroIntegral}
Decimal         = {AnyDigit}+
Float           = {Integer} {Dot} {Decimal}
NonFloat        = ({Float} {Dot})+ {AnyDigit}*
ZeroFloat       = {Zero}{Zero}+{AnyDigit}*{Dot}{AnyDigit}+
%%

<YYINITIAL> {
    {Float}        { list.add(Float.parseFloat(yytext())); }
    {NonFloat}     {}
    {ZeroFloat}    {}
}
