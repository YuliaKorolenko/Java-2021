package search;

public class BinarySearch {
    // Pred: a[-1] = +inf && a[a.length]=-inf
    // Inv : for all i : a[i] >= a[i+1]
    // Post: a[answer-1]>x && a[answer]<=x
    public static int BinaryI(int[] a, int x) {
        int l = -1;
        // l = -1 && l < answer
        int r = a.length;
        // r = a.lenght && r >= answer
        while (l < r - 1) {
            // r - l > 1
            int medium = (r + l) / 2;
            // (r - l > 1 && medium = (r + l)/2) -> (medium > l && medium < r)
            if (x < a[medium]) {
                // x < a[medium] && medium < answer
                l = medium;
                // l' = medium && l' < answer
                // r - l' < r - l
            } else {
                // x >= a[medium] && medium >= answer
                r = medium;
                // r' = medium && r' >= answer
                // r' - l < r - l
            }
            // r' - l' < r - l
        }
        // (l < answer && answer <= r &&  r - l <= 1) -> r= answer
        return r;
    }

    // Pred: a[-1] = +inf && a[a.length]=-inf
    // Inv : for all i : a[i] >= a[i+1]
    // -1 <= l < answer <= r <= a.length
    // Post: a[answer-1]>x && a[answer]<=x
    public static int BinaryR(int[] a, int x, int l, int r) {
        if (l >= r - 1) {
            // ((l < answer && answer <= r &&  r - l <= 1) -> r - l == 1) -> r= answer
            return r;
        }
        else {
            // r - l > 1
            int medium = (r + l) / 2;
            // (r - l > 1 && medium = (r + l)/2) -> (medium > l && medium < r)
            if (x < a[medium]) {
                // x < a[medium] && medium < answer
                l = medium;
                // l' = medium && l' < answer
                // r - l' < r - l
            } else {
                // x >= a[medium] && medium >= answer
                r = medium;
                // r' = medium && r' >= answer
                // r' - l < r - l
            }
            // r' - l' < r - l
            return BinaryR(a, x, l, r);
        }
    }


    public static void main(String[] args) {
        int x = Integer.parseInt(args[0]);
        int[] a = new int[args.length - 1];
        int i = 0;
        while (i < args.length - 1) {
            a[i] = Integer.parseInt(args[i+1]);
            i++;
        }
        int answer1 = BinaryI(a,x);
        int answer2 = BinaryR(a,x,-1, a.length);
        if (answer1==answer2) {
            System.out.println(answer1);
        }
    }
}

