/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jogodavelha;
import java.util.Scanner;

public class JogoDaVelha {
static int x, y;    //posição escolhida
static String jogadas[][] = new String [3][3]; //preenchimento das jogadas
static String marc;  //O ou X

static char vaz0; //vazio na linha, coluna ou diagonal(apenas uma)
static int vaz1; //quantas vazias na linha, coluna ou diagonal
static int vaz2; //quantas vazias no jogo todo
static char verif; //verifica status do jogo
static int qtdRod=0; //quantidade de rodadas
static int user=0; //quantas o usuário ganhou
static int comp=0; //quantas o computador ganhou
static int emp=0; //quantidade de empates
static boolean fim=false; //fim de jogo
static String verJog[] = new String [9]; //adiciona cada letra jogada verificação de jogadas

    public static void main(String[] args) {
        Scanner ler = new Scanner (System.in);
        String resp, jogada;
        
        resp = "S";
        
        do{
            
            System.out.println("  a  |  b  |  c   ");
            System.out.println("-----|-----|------");
            System.out.println("  d  |  e  |  f   ");
            System.out.println("-----|-----|------");
            System.out.println("  g  |  h  |  i   ");
            System.out.print("Faça uma jogada escolhendo uma das letras acima: ");
            jogada = ler.next();
            jogo(jogada);
            System.out.print("Deseja jogar novamente, S/N?: ");
            resp=ler.next();
            resp= resp.toUpperCase();
            for(int i = 0; i<3;i++){
                for(int j=0; j<3;j++){
                    jogadas[i][j]= null;
                }
            }
            for (int i = 0; i<=8;i++){
                verJog[i] = null;
            }
        }while(!"N".equals(resp));
        
        System.out.println("------- PLACAR FINAL -----------");
        System.out.printf("Jogamos %d vez(es)" , qtdRod);
        System.out.println();
        System.out.printf("Você ganhou %d vez(es)" , user);
        System.out.println();
        System.out.printf("Você perdeu %d vez(es)" , comp);
        System.out.println();
        System.out.printf("Empatamos %d vez(es)" , emp);
        System.out.println();
        System.out.print("---------------------------------");
        System.out.println();
    }
public static void jogo(String jogada){
    Scanner leia = new Scanner (System.in);
    qtdRod++;
    marc = "O";
    fim=false;
    verif='Z';
    int v = 0; //número de jogadas em cada rodada
    char ch = jogada.charAt(0); 
    int Quem=1; //controla quem joga: 1 para computador e 0 para usuário
   
    do while(fim==false){
        ch = jogada.charAt(0);
       
        jogada = Character.toString(ch);
        while(ehLetraValida(ch)==false){
              System.out.print("Jogada inválida! Digite uma letra entre A e I: "); 
              jogada = leia.next();
              ch = jogada.charAt(0);
              jogada = Character.toString(ch);
        }
           // while(jogadaRepetida(jogada)==true){
             //   System.out.print("Jogada repetida! Escolha outra letra: "); 
              //  jogada = leia.next();
              //  ch = jogada.charAt(0);
              //  jogada = Character.toString(ch);
            //}
        
        //System.out.println("v="+v);
        verJog[v] = jogada.toUpperCase();
        if (v<=7){v++;};
        char L = 'a';
        posicao(jogada);
        jogadas[x][y] = marc;
        for(int i = 0; i<3;i++){
            for(int j=0; j<3;j++){
              
              if (jogadas[i][j]== null){
                System.out.print("  ");
                System.out.print(L);
                if (j<2){
                    System.out.print("  |");
                }
                else{
                    System.out.print("  ");
                    System.out.println();
                    System.out.print("-----|-----|-----");
                }
             }
              else{
                System.out.print("  ");
                System.out.print(jogadas[i][j]);
                if (j<2){
                    System.out.print("  |");
                }
                else{
                    System.out.print("  ");
                    System.out.println();
                    System.out.print("-----|-----|-----");
                }
              }
            L++;  
            }
            System.out.println("");
        }
        
        
        switch(verif){
            case 'O':{
                System.out.println("Parabéns você ganhou!");
                user++;
                fim=true;
                break;
            }
            case 'X':{
                System.out.println("Você perdeu!");
                comp++;
                fim=true;
                break;
            }
            case 'V':{
                System.out.println("Deu velha!");
                emp++;
                fim=true;
                break;
            }
        }
        
        if (Quem ==1){
            if (fim == false){
                jogada = Character.toString(avalia());
                Quem =0;
                marc = "X";
            }
        }
        else{
            if(fim == false){
                System.out.print("Sua vez de jogar: ");
                jogada = leia.next();
                marc = "O";
                Quem=1;
            }
        }
        if (verif=='O' || verif=='V'){
            jogada = Character.toString(ch);
            if (verif=='O'){
                marc ="O";
            }
        }
        System.out.println("");

   }while(false);
    
}

    public static void posicao(String jogada){
                
        jogada = jogada.toUpperCase();
        switch (jogada){
            case ("A"):{
                x=0;
                y=0;
                break;
            }
            case ("B"):{
                x=0;
                y=1;
               break;
            }
            case ("C"):{
                x=0;
                y=2;
                break;
            }
            case("D"):{
                x=1;
                y=0;
                break;
            }
            case("E"):{
                x=1;
                y=1;
                break;
            }
            case ("F"):{
                x=1;
                y=2;
                break;
            }
            case("G"):{
                x=2;
                y=0;
                break;
            }
            case("H"):{
                x=2;
                y=1;
                break;
            }
            case("I"):{
                x=2;
                y=2;
                break;
            }
            default:{
                System.out.printf("Jogada inválida: %s\n " ,jogada);
               break;
            }
        }
        
    }
   public static boolean ehLetraValida(char ch) {
        ch = Character.toUpperCase(ch);
        return (ch >= 'A' && ch <= 'I'); 
   }
   
   public static boolean jogadaRepetida(String ch) {
       ch = ch.toUpperCase();
       for (int i = 0;i<=8;i++){
           //System.out.println("verJog"+ i +" = " + verJog[i]);
           if (verJog[i] != null && verJog[i].equals(ch)){
               return true;
           } else {
           }
       }
       return false;
   }
   
   public static char avalia(){
       int contO=0, contX=0;
       char vaziaO ='Z';
       char L = 'A';
       vaz2=0;
        for(int i = 0; i<3;i++){    //verificação em linhas
           contO=0;
           contX=0;
           vaz1=0;
            for(int j=0; j<3;j++){
                if (jogadas[i][j]!=null){
                    if("O".equals(jogadas[i][j])){
                        contO++;
                    }
                    else{
                        contX++;
                    }
                }
                else{
                    vaz0 = L;     //qual a jogada livre na linha
                    vaz1++;       //quantas jogadas livres na linha
                vaz2++;       //total de jogadas livres no jogo
                }
                L++;
            }
            if (contO==3){  
            verif='O';
            return 'O';
            }
            if (contX==2 && vaz1==1){
                verif='X';
                return vaz0;
            }
            if (contO==2 && vaz1==1){
                vaziaO = vaz0;
            }
        }
        if (vaziaO!='Z'){
            return vaziaO;
        }
        
        if (vaz2==8){
            return vaz0;
        }
        if (vaz2==0){
            verif='V';
            return 'V';
        }
 //---------------------------------verificação em colunas-----------------------------------       
        L='A';
        vaziaO ='Z';
        for(int i = 0; i<3;i++){    
            contO=0;
            contX=0;
            vaz1=0;
            for(int j=0; j<3;j++){
                if (j>0){L+=3;}
                if (jogadas[j][i]!= null){
                   if("O".equals(jogadas[j][i])){
                    contO++;
                   }
                   else{
                     contX++;
                   }
                }
                else{
                    vaz0 = L;
                    vaz1++;
                }

            }
            L =(char) (L-5); 
           
            if (contO==3){   
                verif='O';
                return 'O';
            }
            if (contX==2 && vaz1==1){
                verif='X';
                return vaz0;
            }
            if (contO==2 && vaz1==1){
                vaziaO = vaz0;
            }
        }
        if (vaziaO!='Z'){
            return vaziaO;
        }
        
 //--------------------------verificação em diagonal a-i--------------------------------
        contO=0;
        contX=0;
        L='A';
        vaz1=0;
        for(int i = 0; i<3;i++){    
            
                if (jogadas[i][i]!= null){
                   if("O".equals(jogadas[i][i])){
                    contO++;
                   }
                   else{
                     contX++;
                   }
                }
                else{
                    vaz0 = L;
                    vaz1++;
                }
                L=+4;
        }
        if (contO==3){   
            verif='O';
            return 'O';
        }
        if (contX==2 && vaz1==1){
            verif='X';
            return vaz0;
        }
        if (contO==2 && vaz1==1){
            return vaz0;
        }
 //-------------------------verificação em diagonal g-c ------------------------------      
        contO=0;
        contX=0;
        L='G';
        vaz1=0;
        for(int i = 0; i <3;i++){    
            
            if (jogadas[2-i][i]!= null){
               if("O".equals(jogadas[2-i][i])){
                contO++;
               }
               else{
                 contX++;
               }
            }
            else{
                vaz0 = L;
                vaz1++;
            }
            L =(char) (L-2);
        }
        if (contO==3){   
            verif='O';
            return 'O';
        }
        if (contX==2 && vaz1==1){
            verif='X';
            return vaz0;
        }
        if (contO==2 && vaz1==1){
            return vaz0;
        }
        if (vaz1>=2){
            return vaz0;
        }
        return vaz0;
}


}  