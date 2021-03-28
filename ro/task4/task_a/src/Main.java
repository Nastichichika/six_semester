public class Main {
    public static void main(String[] args) {
        File file = new File();
        Thread findPhone = new Thread(new Program(file, 1, "Anastasiia"));
        Thread findName = new Thread(new Program(file, 2, "0994586312"));
        Thread addDelete = new Thread(new Program(file, 3, ""
        ));

        findPhone.setDaemon(true);
        findName.setDaemon(true);
        addDelete.setDaemon(true);

        try {
            findPhone.start();
            Thread.sleep(500);
            findName.start();
            Thread.sleep(500);
            addDelete.start();

            findPhone.join();
            findName.join();
            addDelete.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
