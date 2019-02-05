public class Main {
 
/**
* @param args the command line arguments
*/
 
static  char T[]=new char[1000];
static char NT[]=new char[1000];
static char P[][]=new char[60][13];
static String T1="(){}ifdcv;e_=+-*/%,0123456789abghjklmnoprstuvwxyqz";
static String[] p1=new String[60];
 
static String NT1="STBKCLDFGEAHMIJN";
 
static char FT[][]= new char[60][100];
static char FLWT[][]= new char[60][100];
//char P[8][10]={"E->TA","A->+TA","A->e","T->FB","B->*FB","B->e","F->(E)","F->d"};
 
public static void main(String[] args) {
// TODO code application logic here
for(int i1=0;i1<T1.length();i1++){
T[i1]=T1.charAt(i1);
}
 
for(int i=0;i<NT1.length();i++){
NT[i]=NT1.charAt(i);
}
 
p1[0]="S->TC(B){F}";
p1[1]="T->i";p1[2]="T->f";p1[3]="T->d";p1[4]="T->c";p1[5]="T->v";p1[6]="B->TCN";p1[7]="B->e";p1[8]="C->LI";p1[9]="C->_I";p1[10]="F->CAGH;";p1[11]="H->MGH";p1[12]="H->e";p1[13]="K->DE";p1[14]="G->K";p1[15]="G->C";
p1[16]="E->DE";p1[17]="E->e";p1[18]="J->L";p1[19]="J->D";p1[20]="J->_";p1[21]="I->JI";p1[22]="I->e";
p1[23]="A->=";p1[24]="M->+";p1[25]="M->-";p1[26]="M->*";p1[27]="M->/";p1[28]="N->,TCN";p1[29]="N->e";
p1[30]="D->0";p1[31]="D->1";p1[32]="D->2";p1[33]="D->3";p1[34]="D->4";p1[35]="D->5";p1[36]="D->6";p1[37]="D->7";p1[38]="D->8";p1[39]="D->9";
p1[40]="L->a";p1[41]="L->b";p1[42]="L->g";p1[43]="L->h";p1[44]="L->j";p1[45]="L->k";p1[46]="L->l";p1[47]="L->m";p1[48]="L->n";p1[49]="L->o";p1[50]="L->p";
p1[51]="L->q";p1[52]="L->r";p1[53]="L->s";p1[54]="L->t";p1[55]="L->u";p1[56]="L->w";p1[57]="L->x";p1[58]="L->y";p1[59]="L->z";
 
for(int i=0;i<60;i++){
for(int j=0;j<p1[i].length();j++){
//if(p1[i].charAt(j)=='\0')break;
P[i][j]=p1[i].charAt(j);
 
}
}
 
Main nm= new Main();
int i,j;
nm.ComputeFirst();
System.out.print("THE GIVEN GRAMMAR IS::\n");
for(i=0;i<60;i++){
for(int k=0;;k++){
if(P[i][k]=='\0')break;
System.out.print(""+P[i][k]);}
System.out.println();
}
//System.out.print(P[6][3]);
System.out.print("\nThe FIRST for all grammar symbols::\n");
for(i=0;i<16;i++)
{
System.out.print("FIRST("+NT[i]+")={");
for(j=0;j<60;j++)
if(FT[i][j]!='\0')
System.out.print(FT[i][j]+" ");
 
System.out.print("\b}\n");
}
 
nm.ComputeFollow();
System.out.print("\nThe FOLLOW for all grammar symbols::\n");
for(i=0;i<16;i++)
{
System.out.print("FOLLOW("+NT[i]+")={");
for(j=0;j<60;j++)
if(FLWT[i][j]!='\0')
System.out.print(" "+FLWT[i][j]);
 
System.out.print("\b}\n");
 
}
 
}
 
 
void ComputeFollow()
{
int len,i,j,k,added=0;
char sym1,sym2,elm1,elm2,sym;
FLWT[getIndex(NT,'S')][17]='$';
for(int nj=0;nj<2;nj++)
{
for(i=0;i<60;i++)
{
len=p1[i].length();
for(j=3;P[i][j]!='\0';j++)
{
sym1=P[i][j];
sym2=P[i][j+1];
if(isNonTerminal(sym1)==1 && isTerminal(sym2)==1)
FLWT[getIndex(NT,sym1)][getIndex(T,sym2)]=sym2;
}
}
 
for(i=0;i<60;i++)
{
len=p1[i].length();
for(j=3;;j++)
{if(P[i][j]=='\0')break;
sym1=P[i][j];
sym2=P[i][j+1];
if(isNonTerminal(sym1)==1 && isNonTerminal(sym2)==1)
{
for(k=0;k<60;k++)
{
elm1=FLWT[getIndex(NT,sym1)][k];
elm2=FT[getIndex(NT,sym2)][k];
if(elm1!=elm2)
if(elm2!='\0' && elm2!='e')
FLWT[getIndex(NT,sym1)][k]=elm2;
}
}
}
}
 
for(i=0;i<60;i++)
{
sym1=P[i][p1[i].length()-1];
if(isNonTerminal(sym1)==1)
{
sym2=P[i][0];
for(k=0;k<60;k++)
{
elm1=FLWT[getIndex(NT,sym1)][k];
elm2=FLWT[getIndex(NT,sym2)][k];
if(elm1!=elm2)
{
if(elm2!='\0' && elm2!='e')
{
added=1;
FLWT[getIndex(NT,sym1)][k]=elm2;
}
}
}
}
 
sym=P[i][p1[i].length()-1];
if(isNonTerminal(sym)==1 && hasEpsilon(sym)==1)
{
sym1=P[i][p1[i].length()-2];
if(isNonTerminal(sym1)==1)
{
sym2=P[i][0];
for(k=0;k<60;k++)
{
elm1=FLWT[getIndex(NT,sym1)][k];
elm2=FLWT[getIndex(NT,sym2)][k];
if(elm1!=elm2)
{
if(elm2!='\0' && elm2!='e')
{
added=1;
FLWT[getIndex(NT,sym1)][k]=elm2;
}
}
}
}
 
if(i==61)
{
if(added==1)
{
i=-1;
added=0;
}
}
}
}}
}
void ComputeFirst()
{
int added=0,i,j,k;
char X,elm1,elm2;
//clrscr();
 
for(i=0;i<60;i++)
for(j=0;j<60;j++)
FT[i][j]='\0';
 
for(i=0;i<60;i++)
{
X = P[i][3];
if(X=='e')
addEpsilon(P[i][0]);
else if(isTerminal(X)==1)
FT[getIndex(NT,P[i][0])][getIndex(T,X)]=X;
}
 
for(i=0;i<60;i++)
{
X = P[i][3];
if(isNonTerminal(X)==1)
{
for(j=3;;j++)
{if(P[i][j]=='\0')break;
X=P[i][j];
for(k=0;k<60;k++)
{
elm1=FT[getIndex(NT,P[i][0])][k];
elm2=FT[getIndex(NT,X)][k];
if(elm1!=elm2)
{
if(elm2!='\0')
{
added=1;
FT[getIndex(NT,P[i][0])][k]=elm2;
}
}
}
if(hasEpsilon(X)==0)
break;
}
if(j==P[i].length)
{
added=1;
FT[getIndex(NT,P[i][0])][17]='e';
}
}
if(i==61)
{
if(added==1)
{
i=-1;
added=0;
}
}
}
}
private int getIndex(char[] A, char c) {
// throw new UnsupportedOperationException("Not yet implemented");
for(int i=0;A[i]!='\0';i++)
if(A[i]==c)
return i;
 
return -1;
}
private int isNonTerminal(char c)
{
for(int i=0;NT[i]!='\0';i++)
if(NT[i]==c)
return 1;
 
return 0;
}
private int isTerminal(char c)
{
for(int i=0;T[i]!='\0';i++)
if(T[i]==c)
return 1;
 
return 0;
}
 
private int hasEpsilon(char c) {
//throw new UnsupportedOperationException("Not yet implemented");
if(FT[getIndex(NT,c)][17]=='e')
return 1;
return 0;
}
 
private void addEpsilon(char c) {
FT[getIndex(NT,c)][17]='e';
}
}
}
private int getIndex(char[] A, char c) {
// throw new UnsupportedOperationException("Not yet implemented");
for(int i=0;A[i]!='\0';i++)
if(A[i]==c)
return i;
 
return -1;
}
private int isNonTerminal(char c)
{
for(int i=0;NT[i]!='\0';i++)
if(NT[i]==c)
return 1;
 
return 0;
}
private int isTerminal(char c)
{
for(int i=0;T[i]!='\0';i++)
if(T[i]==c)
return 1;
 
return 0;
}
 
private int hasEpsilon(char c) {
//throw new UnsupportedOperationException("Not yet implemented");
if(FT[getIndex(NT,c)][17]=='e')
return 1;
return 0;
}
 
private void addEpsilon(char c) {
FT[getIndex(NT,c)][17]='e';
}
}
