public class Main {
    public static void main(String[] args) {
        File file = new File();
        Program wt = new Program(file, "Anastasiia");
        wt.AddDeleteRecords();
        wt.FindNameByPhone();
        wt.FindPhoneByName();
    }

}
