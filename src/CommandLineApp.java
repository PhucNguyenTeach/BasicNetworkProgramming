import java.io.File;

	public class CommandLineApp {
	    private File currentDir;

	    public CommandLineApp(String defaultDir) {
	        currentDir = new File(defaultDir);
	        if (!currentDir.exists() || !currentDir.isDirectory()) {
	            throw new IllegalArgumentException("Invalid default directory.");
	        }
	    }

	    // Phương thức thay đổi thư mục hiện hành
	    public void changeDirectory(String subfolder) {
	        if (subfolder.equals("..")) {
	            // Quay lại thư mục cha
	            currentDir = currentDir.getParentFile();
	            if (currentDir == null) {
	                System.out.println("No parent directory.");
	                currentDir = new File("C:\\temp"); // Giữ nguyên thư mục mặc định nếu không có thư mục cha
	            }
	        } else {
	            // Chuyển đến thư mục con
	            File newDir = new File(currentDir, subfolder);
	            if (newDir.exists() && newDir.isDirectory()) {
	                currentDir = newDir;
	            } else {
	                System.out.println("Directory not found.");
	            }
	        }
	    }

	    // Phương thức hiển thị nội dung thư mục hiện hành
	    public void listDirectory() {
	        File[] files = currentDir.listFiles();
	        if (files == null) {
	            System.out.println("No files or directories.");
	            return;
	        }

	        for (File file : files) {
	            if (file.isDirectory()) {
	                System.out.println(file.getName().toUpperCase());
	            }
	        }
	        for (File file : files) {
	            if (file.isFile()) {
	                System.out.println(file.getName().toLowerCase());
	            }
	        }
	    }

	    // Phương thức xóa file hoặc thư mục
	    public void deleteFileOrFolder(String name) {
	        File fileOrFolder = new File(currentDir, name);
	        if (fileOrFolder.exists()) {
	            if (fileOrFolder.isFile()) {
	                if (fileOrFolder.delete()) {
	                    System.out.println("File deleted: " + name);
	                } else {
	                    System.out.println("Failed to delete file.");
	                }
	            } else if (fileOrFolder.isDirectory()) {
	                if (fileOrFolder.delete()) { // Thư mục phải rỗng mới xóa được
	                    System.out.println("Folder deleted: " + name);
	                } else {
	                    System.out.println("Failed to delete folder. Make sure it is empty.");
	                }
	            }
	        } else {
	            System.out.println("File/Folder not found.");
	        }
	    }

	    // Phương thức lấy thư mục hiện tại
	    public String getCurrentDirectory() {
	        return currentDir.getAbsolutePath();
	    }

	    // Main chỉ dùng để test các phương thức
	    public static void main(String[] args) {
	        CommandLineApp app = new CommandLineApp("C:\\temp");

	        // Test CD
	        System.out.println("Initial directory: " + app.getCurrentDirectory());
	        app.changeDirectory("a1"); // Thay đổi thành thư mục con 'subfolder'
	        System.out.println("After CD subfolder: " + app.getCurrentDirectory());
	        app.changeDirectory(".."); // Trở về thư mục cha
	        System.out.println("After CD ..: " + app.getCurrentDirectory());

	        // Test DIR
	        System.out.println("Listing directory contents:");
	        app.listDirectory(); // Hiển thị nội dung thư mục hiện hành

	        // Test DELETE
	        app.deleteFileOrFolder("testfile.txt"); // Xóa file hoặc thư mục tên "testfile.txt"
	    }
	}


