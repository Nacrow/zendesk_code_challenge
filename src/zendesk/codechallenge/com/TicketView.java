package zendesk.codechallenge.com;

import java.io.IOException;
import java.rmi.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @author Xinlei Bian
 * @version 1.0
 * @Date Nov 26, 2021
 * @Description Ticket View Class
 */
public class TicketView {

  /**
   * The List is used to store all tickets
   */
  private static List<Ticket> ticketList = new ArrayList<>();

  /**
   * Main Function
   */
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.println("Welcome to the ticket viewer");
    System.out.println("Type 'menu' to view options or 'quit' to exit");
    switch (sc.nextLine()) {
      case "menu":
        break;
      case "quit":
        sc.close();
        System.exit(0);
      default:
        System.out.println("[Failure] Invalid input. Please enter 'menu' or 'quit'");
    }

    while (true) {
      System.out.println("_________________________\n" +
          "Select View Options:\n" +
          "* Press 1 to view all tickets\n" +
          "* Press 2 to view a ticket\n" +
          "* Type 'quit' to exit\n");
      switch (sc.nextLine()) {
        case "1":
          loadTickets();
          showTicketPage();
          break;
        case "2":
          // If tickets have not loaded, load tickets.
          if (ticketList.size() == 0) {
            loadTickets();
          }
          System.out.println("Please enter the ticket ID");
          String line = sc.nextLine();
          // If input is an integer, convert input into int.
          if (isInt(line)) {
            int index = Integer.parseInt(line);
            if (index >= 1 && index <= ticketList.size()) {
              System.out.println(ticketList.get(index - 1).displayCard());
              break;
            }
          }

          // Input is not valid or there are no tickets
          if (ticketList.size() > 0) {
            System.out.println("Please enter a number between 1 ~ " + ticketList.size());
          } else {
            System.out.println("There are no tickets!");
          }
          break;
        case "quit":
          sc.close();
          System.exit(0);
        default:
          System.out.println("[Failure] Invalid input. Please enter 1,2 or 'quit'");
      }
    }
  }

  /**
   * Method to show all tickets
   */
  private static void showTicketPage() {
    Scanner sc = new Scanner(System.in);
    int page = 0;
    boolean output = true;
    if (ticketList.size() <= 25) {
      for (int i = 0; i < ticketList.size(); i++) {
        System.out.println(String.format("%-5d", i + 1) + ticketList.get(i).displayList());
      }
      return;
    }
    while (true) {
      if (output) {
        for (int i = 0; i < 25 && i + page * 25 < ticketList.size(); i++) {
          System.out.println(
              String.format("%-5d", i + page * 25 + 1) + ticketList.get(i + page * 25)
                  .displayList());
        }
        System.out.println("___________________________");
        if (page > 0) {
          System.out.println("Enter 'p' to view previous page");
        }
        if (page * 25 < ticketList.size()) {
          System.out.println("Enter 'n' to view next page");
        }
        System.out.println("Enter 'r' to return to the menu");
      }
      switch (sc.nextLine()) {
        case "p":
          if (page > 0) {
            page--;
            output = true;
          } else {
            System.out.println("[Failure] You are on the first page");
            output = false;
          }
          break;
        case "n":
          if (page < ticketList.size() / 25) {
            page++;
            output = true;
          } else {
            System.out.println("[Failure] You are on the last page");
            output = false;
          }
          break;
        case "r":
          return;
        default:
          System.out.println("[Failure] Invalid input.");
          output = false;
      }
    }

  }

  /**
   * Method to request and load all tickets into ticketList
   */
  private static void loadTickets() {
    // Clear the list
    ticketList.clear();
    String url = AuthUtil.getUrl() + "?page[size]=100";
    // Load all tickets
    boolean hasNext = true;
    while (hasNext) {
      JSONObject js;
      try {
        js = new JSONObject(HttpUtil.httpRequest(url));
        readTickets(js.getJSONArray("tickets"));
        hasNext = js.getJSONObject("meta").getBoolean("has_more");
        url = js.getJSONObject("links").getString("next");
      } catch (IOException e) {
        String errorMessage = e.getMessage();
        if (errorMessage.contains("401")) {
          System.out.println(
              "[Failure] Error code 401 : User has to be authenticated to proceed. Wrong email or token");
          System.exit(-1);
        } else if (errorMessage.contains("403")) {
          System.out.println("[Failure] Error Code 403 : User is not allowed to proceed");
          System.exit(-1);
        } else if (e instanceof UnknownHostException) {
          System.out.println("[Failure] UnknownHostException:" + e.getMessage());
          System.exit(-1);
        } else {
          System.out.println("Page doesn't exist or there was another problem");
          e.printStackTrace();
          System.out.println(errorMessage);
          System.exit(-1);
        }
      } catch (JSONException j) {
        System.out.println("[Failure] Error in loading tickets: " + j.getMessage());
        System.exit(-1);
        return;
      }
    }
  }

  /**
   * Read all Tickets from a JSONArray
   */
  private static void readTickets(JSONArray ticketsJson) {
    System.out.println("Congratulations! We got " + ticketsJson.length() + " tickets!");
    for (int i = 0; i < ticketsJson.length(); i++) {
      Ticket ticket = new Ticket();
      ticket.loadTicketFromJson(ticketsJson.getJSONObject(i));
      ticketList.add(ticket);
    }
  }

  /**
   * Check whether a string can be converted into an Integer
   */
  public static boolean isInt(String num) {
    if (num.length() >= 10 || num.length() == 0) {
      return false;
    }
    for (char c : num.toCharArray()) {
      if (!Character.isDigit(c)) {
        return false;
      }
    }
    return true;
  }
}
