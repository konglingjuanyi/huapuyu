%{
#include <stdio.h>
#include "bison.hpp"

/*
extern "C"
{
	extern char* strdup(char*);
	int yylex(void);
	int yywrap(void);
}
yylval = atoi(yytext); 
char* yylval;
YYSTYPE yylval;

Monitor Info: CALLPK=($10.9.213.141_1245081595008$),UIId=($null$),OpId=($null$),OrgId=($null$),ChannelId=($null$),ClientIP=($10.9.213.141$),CallSequence=($10.9.213.141_1245081595008$),WebPartitionId=($query:standard$),WebServerIP=($10.10.105.91$),CALLID=($2$),PARENTCALLID=($1$),MONITORDATATYPE=($METHOD$),STATEMENT=($com.asiainfo.boss30.common.IAppMonitor4BOMS_AIProxy.testHlrMgnt()$),STARTTIME=($2009-06-15 23:59:55:008$),FINISHTIME=($2009-06-15 23:59:55:021$),TOTALTIME=($13ms$), Monitor End
*/
%}

CHAR [" "A-Za-z0-9\.\_\-\(\)\:]
NUMBER [0-9]
DELIM [" "\n\t]
NUMBERS {NUMBER}+
WHITESPACE {DELIM}+
STRINGS {CHAR}+

LPAREN [(]
RPAREN [)]
LBRACE [{]
RBRACE [}]
LBRACKET [<]
RBRACKET [>]
SEMICOLON [;]
COMMA [,]
DOT [.]
COLON [:]
DOLLAR [$]
UNDERLINE [_]
EQUAL [=]
DASH [-]

%%

"@Monitor Info: " return ROWBEGIN;
", Monitor End@" return ROWEND;
{LPAREN} return LPAREN;
{RPAREN} return RPAREN;
{COLON} return COLON;
{DOLLAR} return DOLLAR;
{COMMA} return COMMA;
{EQUAL} return EQUAL;
{STRINGS} { yylval = strdup(yytext); return STRINGS; }
{WHITESPACE} ;

%%

int yywrap(void)
{
	printf("Parse finish!\n");
	return 0;
}


