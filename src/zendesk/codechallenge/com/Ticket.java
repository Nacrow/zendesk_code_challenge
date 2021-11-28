package zendesk.codechallenge.com;

import java.util.HashSet;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author Xinlei Bian
 * @version 1.0
 * @Date Nov 26, 2021
 * @Description Ticket Class
 */
public class Ticket {

  private int id;
  private String createDate;
  private String updateDate;
  private String type;
  private String subject;
  private String description;
  private String priority;
  private String status;
  private String requesterId;
  private Set<String> tags;

  public Ticket() {
    tags = new HashSet<>();
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getCreateDate() {
    return createDate;
  }

  public void setCreateDate(String createDate) {
    this.createDate = createDate;
  }

  public String getUpdateDate() {
    return updateDate;
  }

  public void setUpdateDate(String updateDate) {
    this.updateDate = updateDate;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getPriority() {
    return priority;
  }

  public void setPriority(String priority) {
    this.priority = priority;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getRequesterId() {
    return requesterId;
  }

  public void setRequesterId(String requesterId) {

    this.requesterId = requesterId;
  }

  public Set<String> getTags() {
    return tags;
  }

  public void addTags(String tag) {
    tags.add(tag);
  }

  /**
   * List all tags in a string
   */
  public String listTags() {
    StringBuilder sb = new StringBuilder();
    for (String tag : tags) {
      sb.append(tag).append(" ");
    }
    return sb.toString();
  }

  /**
   * Display a ticket in card view
   */
  public String displayCard() {
    String sb = getId() + " " + "Subject:" + getSubject() + "\n" +
        "Description"+ getDescription() + "\n" +
        "___________________________\n" +
        "Requester ID:" + getRequesterId() + "\n" +
        "Priority:" + getPriority() + " Status:" +
        getStatus() + " Type:" + getType() + "\n" +
        "Tags:" + listTags() + "\n" +
        "Create Date:" + getCreateDate() + " Update Date:" + getUpdateDate();
    return sb;
  }

  /**
   * Display a ticket in list view
   */
  public String displayList() {
    String shortSubject = getSubject();
    if (getSubject().length() > 60) {
      shortSubject = getSubject().substring(0, 57);
      shortSubject += "...";
    }
    String str = String.format("|Subject:%-60s|Requester ID:%-12s|Create Date:%s", shortSubject,
        getRequesterId(), getCreateDate());
    return str;
  }

  /**
   * Update ticket information from JSONObject
   *
   * @param json JSONObject used to update ticket information
   */
  public void loadTicketFromJson(JSONObject json) {
    setId(json.getInt("id"));
    setCreateDate(json.getString("created_at"));
    setUpdateDate(json.getString("updated_at"));
    if (json.get("subject") != null && !json.isNull("subject")) {
      setSubject(json.getString("subject"));
    }
    if (json.get("tags") != null && !json.isNull("tags")) {
      JSONArray tag = json.getJSONArray("tags");
      for (int i = 0;i < tag.length();i++){
        addTags(tag.getString(i));
      }
    }
    if (json.get("description") != null && !json.isNull("description")) {
      setDescription(json.getString("description"));
    }
    if (json.get("type") != null && !json.isNull("type")) {
      setType(json.getString("type"));
    }
    if (json.get("priority") != null && !json.isNull("priority")) {
      setPriority(json.getString("priority"));
    }
    if (json.get("status") != null && !json.isNull("status")) {
      setStatus(json.getString("status"));
    }
    if (json.get("requester_id") != null && !json.isNull("requester_id")) {
      setRequesterId(String.valueOf(json.getLong("requester_id")));
    }
  }
}
