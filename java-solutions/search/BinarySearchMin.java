package search;

public class BinarySearchMin {

    // Pred: exist max (for all i<=max a[i]>a[a.length-1] && a[i-1]<a[i], for all i>max a[i]<=a[a.length-1] && a[i+1]>a[i])
    // exist min = max + 1

    // Inv : a[l]>a[min] && a[r]>=a[min]
    // -1 <= l < min <= r <= a.length
    // a[-1] = -inf && a[a.length]=+inf

    // Post: R=a[min]
    public static int BinarySearchMinI(int[] a) {
        int l = -1;
        // l=-1
        int r = a.length;
        // r=a.length
        while (r - l > 1) {
            // r - l > 1
            int medium = (l + r) / 2;
            // (r - l > 1 && medium = (r + l)/2) -> (medium > l && medium < r)
            if (a[medium] > a[a.length - 1]) {
                // a[medium]>a[a.length - 1] -> medium<=max
                l = medium;
                // l' = medium && l' < min
                // r - l' < r - l
            } else {
                // a[medium]<=a[a.length - 1] -> medium>max
                r = medium;
                // r' = medium && r' >= min
                // r' - l < r' - l
            }
            // r' - l' < r - l && l' < min && r' >= min
        }
        // (l < min && min <= r &&  r - l <= 1) -> r= min
        return a[r];
    }

    // Pred: exist max (for all i<=max a[i]>a[a.length-1]
    // && a[i-1]<a[i], for all i>max a[i]<=a[a.length-1] && a[i+1]>a[i])
    // exist min = max + 1 && Inv

    // Post: R=a[min]
    public static int BinarySearchMinR(int[] a, int l, int r) {
        if (r - l <= 1) {
            // (l < min && min <= r &&  r - l <= 1) -> r = min
            return a[r];
        } else {
            // r - l > 1
            int medium = (l + r) / 2;
            // (r - l > 1 && medium = (r + l)/2) -> (medium > l && medium < r)
            if (a[medium] > a[a.length - 1]) {
                // a[medium]>a[a.length - 1] -> medium<=max
                l = medium;
                // l' = medium && l' < min
                // r - l' < r - l
            } else {
                // a[medium]<=a[a.length - 1] -> medium>max
                r = medium;
                // r' = medium && r' >= min
                // r' - l < r' - l
            }
            // r' - l' < r - l  && l' < min && r' >= min
            return BinarySearchMinR(a, l, r);
        }
    }

    public static void main(String[] args) {
        int[] a = new int[args.length];
        int i = 0;
        while (i < args.length) {
            a[i] = Integer.parseInt(args[i]);
            i++;
        }
        int answer1 = BinarySearchMinI(a);
        int answer2 = BinarySearchMinR(a, -1, a.length);
        if (answer1 == answer2) {
            System.out.println(answer1);
        }
    }
}
