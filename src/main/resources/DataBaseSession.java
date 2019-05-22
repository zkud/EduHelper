package edu.bsuirDev;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class DataBaseSession {
    public static void main(String[] args) {
        DataBaseSession dataBaseSession = new DataBaseSession();
        dataBaseSession.deleteStep(1, 14,9);
    }

    public String getDBData(URL url) throws IOException {
        Scanner scanner;
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        scanner = new Scanner(new BufferedInputStream(urlConnection.getInputStream())).useDelimiter("\\A");
        String result = scanner.hasNext() ? scanner.next() : "";
        return result;
    }

    public String loginUser(String name, String mail, String password) {
        try {
            URL url = new URL("http://localhost:8080/login?name=" +
                    name + "&mail=" + mail + "&password=" + password + "&flag=false");
            return getDBData(url);
        } catch(IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public String registerUser(String name, String mail, String password) {
        try {
            URL url = new URL("http://localhost:8080/login?name=" +
                    name + "&mail=" + mail + "&password=" + password + "&flag=true");
            return getDBData(url);
        } catch(IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public String makePlan(String plan) {
        try {
            URL url = new URL("http://localhost:8080/login?plan=" + plan);
            return getDBData(url);
        } catch(IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getIndex(long id) {
        try {
            URL url = new URL("http://localhost:8080/index?id=" + id);
            return getDBData(url);
        } catch(IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public String getPlanInfo(long userid, long planid) {
        try {
            URL url = new URL("http://localhost:8080/planinfo?userid=" + userid +"&planid=" + planid);
            return getDBData(url);
        } catch(IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void deleteUser(long userid) {
        try {
            URL url = new URL("http://localhost:8080/delete/user?userid=" + userid);
            getDBData(url);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    public void deletePlan(long userid, long planid) {
        try {
            URL url = new URL("http://localhost:8080/delete/plan?userid=" + userid + "&planid=" + planid);
            getDBData(url);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    public void deleteStep(long userid, long planid, long stepid) {
        try {
            URL url = new URL("http://localhost:8080/delete/step?userid="
                    + userid + "&planid=" + planid + "&stepid=" + stepid);
            getDBData(url);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
