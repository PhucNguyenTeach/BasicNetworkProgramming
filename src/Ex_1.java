import java.io.File;
import java.io.FilenameFilter;

public class Ex_1 {

	public static boolean delete(String path) {
		File file = new File(path);
		if (file.exists()) {
			if (file.isDirectory()) {
				return deleteRecursively(file);
			}
			if (file.isFile()) {
				return file.delete();
			}
		}
		return false;
	}

	public static boolean deleteRecursively(File file) {
		if (file != null) {
			File[] listFile = file.listFiles();
			if (listFile != null) {
				for (File f : listFile) {
					if (!deleteRecursively(f)) {
						return false;
					}
				}
			}
		}
		return file.delete();
	}
	
	// delete extended
	public static boolean deleteExtension(String path) {
		File file = new File(path);
		if (file.exists()) {
			if (file.isFile()) {
				return file.delete();
			}
			if (file.isDirectory()) {
				return deleteRecursivelyExtension(file);
			}
		}
		return false;
	}

	private static boolean deleteRecursivelyExtension(File file) {
		File[] files = file.listFiles();
		if (files != null) {
			for (File f : files) {
				if (f.isFile()) {
					if (!f.delete()) {
						return false;
					}
				} else if (f.isDirectory()) {
					deleteRecursivelyExtension(f);
				}

			}
		}
		return true;
	}

	public static void main(String[] args) {
		delete("");
	}
}
