//import java.util.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;
import java.net.*;
//import java.util.Scanner;
import java.util.Map;
import java.io.*;
import java.util.Scanner;

public class SteamAPIProject {
    public static void main(String[] args) {
        String steamID="76561198205537151";
        /*Code that will be implemented later. This code allows the user to input their own steam id, and will tell
        them if it is invalid, based on its length and content. steam ids are a string of 17 base-10 digits.
        System.out.println("Please enter your Steam ID. It is a 17-digit number found on your profile page.");
        Scanner getSteamID=new Scanner(System.in);
        String steamID=getSteamID.nextLine();
        if(steamID.length()!=17){
            System.out.println("invalid Steam ID, entry is not 17 characters);
        }else{
            try{
                Integer.valueOf(SteamID);
            }catch{
                System.out.println("invalid Steam ID, entry contains non numeric characters);
            }
        }*/
        String searchURL = "http://api.steampowered.com/IPlayerService/GetOwnedGames/v0001/?key=B8BAAE1216CB3A2F7A5FBC229384E455&steamid="+steamID+"&format=jso";
        try {
            System.out.println("What are you looking for?\n1) unplayed games\n2) games to revisit\n3) games with new content");
            Scanner getAnswer=new Scanner(System.in);
            String answer=getAnswer.nextLine();
            try{
                int intAnswer=Integer.parseInt(answer);
            }catch (Exception f){
                System.out.println("please enter a 1, 2, or 3");
            }
            URL tester = new URL(searchURL);
            BufferedReader in=new BufferedReader(new InputStreamReader(tester.openStream()));
            JSONParser parser=new JSONParser();
            JSONObject userInfo=(JSONObject)parser.parse(in);
            Map response=(Map) userInfo.get("response");
            JSONArray gameArray = (JSONArray)response.get("games");
            for(int i=0; i < gameArray.size();i++) {
                Map game = (Map) gameArray.get(i);
                System.out.println(game.get("appid"));
            }
        } catch (Exception e) {
            System.out.print("Could not connect.\nMake sure you are connected to the internet,\nthat steam is unblocked on your network,\nthat your steam id is valid,\nand that your game information is public.");
        }
    }
}