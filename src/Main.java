import java.text.Normalizer;
import java.util.Scanner;

public class Main {
    /*
    ㅁ : 4358, 4535
    ㅂ : 4359, 4536
    ㅇ : 4363, 4540
    ㅎ : 4370, 4546 //추가버전
    ㅅ : 4361, 4538 //추가버전
     */
    enum MOD {엉엉이, 멈뭄미, 법붑비, 섯섯이, 하트하트}

    public static String[] 저주멍멍이들 = {
            "멈뭄미",
            "법붑비",
            "엉엉이",
//            "하트하트",
//            "섯섯이"
    };

    public static char[] 초성들 = {4358, 4359, 4363, 4370, 4361};

    public static char[] 종성들 = {4535, 4536, 4540, 4546, 4538};

    public static int 선택 = 0;

    public static CharSequence 저주(String nfd, int mod) {
        StringBuilder nfc = new StringBuilder(nfd);
        for(int i=0; i<nfd.length(); i++) {
            switch (nfd.codePointAt(i)) {
                case 4358:
                case 4359:
                case 4363:
//                case 4370:
//                case 4361:
                    nfc.setCharAt(i, 초성들[mod]);
                    break;
                case 4535:
                case 4536:
                case 4540:
//                case 4546:
//                case 4538:
                    nfc.setCharAt(i, 종성들[mod]);
                    break;
            }
        }
        return nfc;
    }

    public static void main(String[] args) {
        boolean 적절 = false;
        String input = "초기값";
        Scanner s = new Scanner(System.in);
        System.out.println("저주의 멍멍이들!! 끝내고 싶으면 \"사료주기\" 입력하세요.");
        System.out.println("저주의 멍멍이를 선택하세요! (숫자, 이름 둘다가능)");
        for(int i=0; i<저주멍멍이들.length; i++) {
            System.out.println(i + " : " + 저주멍멍이들[i]);
        }
        while (!적절) {
            System.out.print("당신의 선택은 : ");
            input = s.nextLine();
            if(isNumeric(input)) {
                선택 = Integer.parseInt(input);
                if(선택 >= 0 && 선택 <= 2 ) {
                    적절 = true;
                }
            } else {
                for(int i=0; i<저주멍멍이들.length; i++) {
                    if(저주멍멍이들[i].equals(input)) {
                        선택 = i;
                        적절 = true;
                        break;
                    }
                }
            }
            if(!적절) {
                System.out.println("부적절한 선택! 다시 선택하세요!");
            }
        }
        System.out.println(저주멍멍이들[선택] + "!!!");
        while (!input.equals("사료주기")) {
            System.out.print("당신 > ");
            input = s.nextLine();
            String nfd = Normalizer.normalize(input, Normalizer.Form.NFD);
            System.out.println(저주멍멍이들[선택] + " > " + Normalizer.normalize(저주(nfd, 선택), Normalizer.Form.NFC));
        }
        System.out.println("날으는 고슴도치가 나타나 모든 저주를 풀어주었습니다!!");
    }

    public static boolean isNumeric(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch(NumberFormatException e) {
            return false;
        }
    }
}
