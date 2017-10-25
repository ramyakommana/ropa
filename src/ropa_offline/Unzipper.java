package ropa_offline;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.testng.annotations.Test;

import page_objects.Ropa_pom;

/**
 * @author KRamyaSree
 *
 * QA_Ropa
* Jul 5, 2017
* 7:15:16 PM
 */
public class Unzipper extends Ropa_pom {
	 List<String> fileList;

	    public static void main(String [] args){
	       log.info("unzipping");         
	    }
	    
	    public void zip(){

	    	Unzipper unzipper = new Unzipper();
	        unzipper.unzipZipsInDirTo(Paths.get(prop.getProperty("downloadFilepath_md")), Paths.get(prop.getProperty("md_unzipped_file")));  
	        }
	    
	    public void zip1(){

	    	Unzipper unzipper = new Unzipper();
	        unzipper.unzipZipsInDirTo(Paths.get(prop.getProperty("downloadFilepath_phy")), Paths.get(prop.getProperty("phy_unzipped_file")));  
	        }


	    public void unzipZipsInDirTo(Path searchDir, Path unzipTo ){

	        final PathMatcher matcher = searchDir.getFileSystem().getPathMatcher("glob:**/*.zip");
	        try (final Stream<Path> stream = Files.list(searchDir)) {
	            stream.filter(matcher::matches)
	                    .forEach(zipFile -> unzip(zipFile,unzipTo));
	        }catch (IOException e){
	            //handle your exception
	        }
	    }

	    public void unzip(Path zipFile, Path outputPath){
	        try (ZipInputStream zis = new ZipInputStream(Files.newInputStream(zipFile))) {

	            ZipEntry entry = zis.getNextEntry();

	            while (entry != null) {

	                Path newFilePath = outputPath.resolve(entry.getName());
	                if (entry.isDirectory()) {
	                    Files.createDirectories(newFilePath);
	                } else {
	                    if(!Files.exists(newFilePath.getParent())) {
	                        Files.createDirectories(newFilePath.getParent());
	                    }
	                    try (OutputStream bos = Files.newOutputStream(outputPath.resolve(newFilePath))) {
	                        byte[] buffer = new byte[Math.toIntExact(entry.getSize())];

	                        int location;

	                        while ((location = zis.read(buffer)) != -1) {
	                            bos.write(buffer, 0, location);
	                        }
	                    }
	                }
	                entry = zis.getNextEntry();
	            }
	        }catch(IOException e){
	            throw new RuntimeException(e);
	            //handle your exception
	        }
	    }
}


