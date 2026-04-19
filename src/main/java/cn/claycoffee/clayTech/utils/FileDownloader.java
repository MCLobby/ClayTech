package cn.claycoffee.clayTech.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.jetbrains.annotations.NotNull;

import com.google.gson.JsonArray;

import cn.claycoffee.clayTech.ClayTech;
import cn.claycoffee.clayTech.ClayTechData;

@Deprecated
public class FileDownloader {

    public static @NotNull String updateFunc(@NotNull String urlp, String fileName, @NotNull String savePath, JsonArray ja) {
        try {
            if (ClayTech.getInstance().getConfig().getBoolean("replace-when-server-stops")) {
                ClayTechData.updateJar = getFileData(urlp);
                ClayTechData.jarLocation = savePath + File.separator + fileName;
                return savePath + File.separator + fileName;
            }
            URL url = new URL(urlp);
            HttpURLConnection conne = (HttpURLConnection) url.openConnection();
            conne.setConnectTimeout(5 * 1000);
            conne.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

            InputStream input = conne.getInputStream();
            byte[] getdata = readInputStream(input);

            File saveDir = new File(savePath);
            if (!saveDir.exists()) {
                saveDir.mkdir();
            }
            FileOutputStream fos = new FileOutputStream(ClayTech.getInstance().getFile());
            fos.write(getdata);
            fos.close();
            input.close();
            return saveDir + File.separator + fileName;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }

    }

    public static @NotNull String downloadFile(@NotNull String urlp, String fileName, @NotNull String savePath) {
        try {
            URL url = new URL(urlp);
            HttpURLConnection conne = (HttpURLConnection) url.openConnection();
            conne.setConnectTimeout(5 * 1000);
            conne.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

            InputStream input = conne.getInputStream();
            byte[] getdata = readInputStream(input);

            File saveDir = new File(savePath);
            if (!saveDir.exists()) {
                saveDir.mkdir();
            }
            File file = new File(saveDir + File.separator + fileName);
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(getdata);
            fos.close();
            input.close();
            return saveDir + File.separator + fileName;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public static byte[] getFileData(@NotNull String urlp) {
        try {
            URL url = new URL(urlp);
            HttpURLConnection conne = (HttpURLConnection) url.openConnection();
            conne.setConnectTimeout(5 * 1000);
            conne.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

            InputStream input = conne.getInputStream();
            byte[] getdata = readInputStream(input);
            input.close();
            return getdata;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static byte[] readInputStream(@NotNull InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int leng = 0;
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        while ((leng = inputStream.read(buffer)) != -1) {
            b.write(buffer, 0, leng);
        }
        b.close();
        return b.toByteArray();
    }
}
