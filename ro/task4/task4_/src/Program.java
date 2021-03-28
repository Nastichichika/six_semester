public class Program{
    private File file;
    private String task_string;
    public Program(File file,  String task_string) {
        this.file = file;
        this.task_string = task_string;
    }


    void FindPhoneByName() {
        new Thread (()-> {
            boolean find = false;
            while (!find) {
                file.LockRead();
                System.out.println("Search phone by name: Start.");
                for (int i = 0; i < file.records.size(); i++) {
                    if (file.records.get(i).getName().equals(task_string)) {
                        System.out.println("Search phone by name: Found " + file.records.get(i).getNumber());
                        find = true;
                    }
                }
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Search phone by name: Finish.");

                file.UnlockRead();
            }
        }).start();
    }
    void FindNameByPhone() {
        new Thread (()-> {
            boolean find = false;
            while (!find) {
                file.LockRead();
                System.out.println("Search name by phone: Start.");
                for (int i = 0; i < file.records.size(); i++) {
                    if (file.records.get(i).getNumber().equals(task_string)) {
                        System.out.println("Search name by phone: Found " + file.records.get(i).getName());
                        find = true;
                    }
                }
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Search name by phone: Finish.");
                file.UnlockRead();

            }
        }).start();
    }

    void AddDeleteRecords() {
            new Thread (()-> {

                Record record1 = new Record("Aleksander", "Addams", "0506584251");
                Record record2 = new Record("Max", "Fowler", "0956963312");
                boolean choose = false;

                while (true) {
                    file.LockWrite();
                    System.out.println("Add records: Start.");
                    if (choose) file.records.add(record1);
                    else file.records.add(record2);
                    for (int i = 0; i < file.records.size(); i++) {
                        System.out.println(file.records.get(i).toString());
                    }
                    System.out.println("Add records: Finish.");
                    file.UnlockWrite();

                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    file.LockWrite();
                    System.out.println("Delete records: Start.");
                    if (choose) file.records.remove(record1);
                    else file.records.remove(record2);
                    for (int i = 0; i < file.records.size(); i++) {
                        System.out.println(file.records.get(i).toString());
                    }
                    System.out.println("Delete records: Finish.");
                    file.UnlockWrite();
                    choose = !choose;
                }
            }).start();
    }
}
