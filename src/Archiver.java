import java.io.BufferedReader;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Paths;
import java.nio.file.Paths;
import java.util.zip.ZipFile;

public class Archiver {
    public static void main(String[] args) throws Exception {
        Operation operation = null;
        do {
            try {
                operation = askOperation();
                CommandExecutor.execute(operation);
            } catch (WrongZipFileException e) {
                ConsoleHelper.writeMessage("Вы не выбрали файл архива или выбрали неверный файл");
            } catch (Exception e) {
                ConsoleHelper.writeMessage("Произошла ошибка. Проверьте входные данные. ");
            }
        } while (operation != Operation.EXIT);
    }
    public static Operation askOperation() throws IOException {
        ConsoleHelper.writeMessage("");
        ConsoleHelper.writeMessage("Выберете операцию:");
        ConsoleHelper.writeMessage(String.format("\t %d - упаковать файлы в архив" , Operation.CREAT.ordinal()));
        ConsoleHelper.writeMessage(String.format("\t %d - добавить файл в архив" , Operation.ADD.ordinal()));
        ConsoleHelper.writeMessage(String.format("\t %d - удалить файл из архива" , Operation.REMOVE.ordinal()));
        ConsoleHelper.writeMessage(String.format("\t %d - распаковать архив", Operation.EXTRACT.ordinal()));
        ConsoleHelper.writeMessage(String.format("\t %d - посмотреть содержимое архива", Operation.CONTENT.ordinal()));
        ConsoleHelper.writeMessage(String.format("\t %d - выход" , Operation.EXIT.ordinal()));

        return Operation.values()[ConsoleHelper.readInt()];
    }
}