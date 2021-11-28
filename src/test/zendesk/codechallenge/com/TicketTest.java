package test.zendesk.codechallenge.com;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.json.JSONObject;
import org.junit.Test;
import zendesk.codechallenge.com.Ticket;

/**
 * zendesk.codechallenge.com.Ticket Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @Date Nov 25, 2021
 */
public class TicketTest {

  /**
   * Method: addTags(String tag)
   */
  @Test
  public void testAddTags() throws Exception {
    Ticket ticket1 = new Ticket();
    ticket1.addTags("tag1");
    ticket1.addTags("tag1");
    ticket1.addTags("tag2");
    assertEquals(ticket1.listTags(), "tag1 tag2 ");

    Ticket ticket2 = new Ticket();
    ticket2.addTags("tag1");
    ticket2.addTags("tag2");
    ticket2.addTags("tag2");
    ticket2.addTags("tag2");
    ticket2.addTags("tag2");
    ticket2.addTags("tag3");
    assertEquals(ticket2.listTags(), "tag1 tag2 tag3 ");
  }

  /**
   * Method: displayList()
   */
  @Test
  public void testDisplayList1() throws Exception {
    Ticket ticket = new Ticket();
    ticket.setId(3);
    ticket.setDescription("Hello");
    ticket.setSubject("hi");
    ticket.setStatus("open");
    ticket.setCreateDate("11/25/2021 09:00:01");
    ticket.setUpdateDate("11/25/2021 09:01:02");
    ticket.setRequesterId("1223432234");
    ticket.setType("incident");
    assertEquals(
        "|Subject:hi                                                          |Requester ID:1223432234  |Create Date:11/25/2021 09:00:01",
        ticket.displayList());
  }

  @Test
  public void testDisplayList2() throws Exception {
    Ticket ticket = new Ticket();
    ticket.setId(3);
    ticket.setDescription("Hello");
    ticket.setSubject(
        "HiHiHiHiHiHiHiHiHiHiHiHiHiHiHiHiHiHiHiHiHiHiHiHiHiHiHiHiHiHiHiHiHiHiHiHiHiHiHi");
    ticket.setStatus("open");
    ticket.setCreateDate("11/25/2021 09:00:01");
    ticket.setUpdateDate("11/25/2021 09:01:02");
    ticket.setRequesterId("1223432234");
    ticket.setType("incident");
    assertEquals(
        "|Subject:HiHiHiHiHiHiHiHiHiHiHiHiHiHiHiHiHiHiHiHiHiHiHiHiHiHiHiHiH...|Requester ID:1223432234  |Create Date:11/25/2021 09:00:01",
        ticket.displayList());
  }

  /**
   * Method: loadTicketFromJson(JSONObject json)
   */
  @Test
  public void testLoadTicketFromJson1() throws Exception {
    String jsonString = "{\"url\":\"https://zccbian.zendesk.com/api/v2/tickets/5.json\",\"id\":5,\"external_id\":null,\"via\":{\"channel\":\"api\",\"source\":{\"from\":{},\"to\":{},\"rel\":null}},\"created_at\":\"2021-11-25T02:48:45Z\",\"updated_at\":\"2021-11-25T02:48:45Z\",\"type\":null,\"subject\":\"velit eiusmod reprehenderit officia cupidatat\",\"raw_subject\":\"velit eiusmod reprehenderit officia cupidatat\",\"description\":\"Aute ex sunt culpa ex ea esse sint cupidatat aliqua ex consequat sit reprehenderit. Velit labore proident quis culpa ad duis adipisicing laboris voluptate velit incididunt minim consequat nulla. Laboris adipisicing reprehenderit minim tempor officia ullamco occaecat ut laborum.\\n\\nAliquip velit adipisicing exercitation irure aliqua qui. Commodo eu laborum cillum nostrud eu. Mollit duis qui non ea deserunt est est et officia ut excepteur Lorem pariatur deserunt.\",\"priority\":null,\"status\":\"open\",\"recipient\":null,\"requester_id\":421918360752,\"submitter_id\":421918360752,\"assignee_id\":421918360752,\"organization_id\":361633709151,\"group_id\":360022313711,\"collaborator_ids\":[],\"follower_ids\":[],\"email_cc_ids\":[],\"forum_topic_id\":null,\"problem_id\":null,\"has_incidents\":false,\"is_public\":true,\"due_at\":null,\"tags\":[\"est\",\"incididunt\",\"nisi\"],\"custom_fields\":[],\"satisfaction_rating\":null,\"sharing_agreement_ids\":[],\"fields\":[],\"followup_ids\":[],\"ticket_form_id\":360003541511,\"brand_id\":360007074831,\"allow_channelback\":false,\"allow_attachments\":true}";
    Ticket ticket = new Ticket();
    JSONObject json = new JSONObject(jsonString);
    ticket.loadTicketFromJson(json);
    assertEquals(ticket.getId(), 5);
    assertNull(ticket.getType());
    assertNull(ticket.getPriority());
    assertEquals("open", ticket.getStatus());
    assertEquals("2021-11-25T02:48:45Z", ticket.getCreateDate());
    assertEquals("2021-11-25T02:48:45Z", ticket.getUpdateDate());
    assertEquals("421918360752", ticket.getRequesterId());
    assertEquals("velit eiusmod reprehenderit officia cupidatat", ticket.getSubject());
    assertEquals(
        "Aute ex sunt culpa ex ea esse sint cupidatat aliqua ex consequat sit reprehenderit. Velit labore proident quis culpa ad duis adipisicing laboris voluptate velit incididunt minim consequat nulla. Laboris adipisicing reprehenderit minim tempor officia ullamco occaecat ut laborum.\n\nAliquip velit adipisicing exercitation irure aliqua qui. Commodo eu laborum cillum nostrud eu. Mollit duis qui non ea deserunt est est et officia ut excepteur Lorem pariatur deserunt.",
        ticket.getDescription());
  }

  @Test
  public void testLoadTicketFromJson2() throws Exception {
    String jsonString = """
        {
          "assignee_id": 235323,
          "collaborator_ids": [
            35334,
            234
          ],
          "created_at": "2009-07-20T22:55:29Z",
          "custom_fields": [
            {
              "id": 27642,
              "value": "745"
            },
            {
              "id": 27648,
              "value": "yes"
            }
          ],
          "description": "The fire is very colorful.",
          "due_at": null,
          "external_id": "ahg35h3jh",
          "follower_ids": [
            35334,
            234
          ],
          "group_id": 98738,
          "has_incidents": false,
          "id": 35436,
          "organization_id": 509974,
          "priority": "high",
          "problem_id": 9873764,
          "raw_subject": "{{dc.printer_on_fire}}",
          "recipient": "support@company.com",
          "requester_id": 20978392,
          "satisfaction_rating": {
            "comment": "Great support!",
            "id": 1234,
            "score": "good"
          },
          "sharing_agreement_ids": [
            84432
          ],
          "status": "open",
          "subject": "Help, my printer is on fire!",
          "submitter_id": 76872,
          "tags": [
            "enterprise",
            "other_tag"
          ],
          "type": "incident",
          "updated_at": "2011-05-05T10:38:52Z",
          "url": "https://company.zendesk.com/api/v2/tickets/35436.json",
          "via": {
            "channel": "web"
          }
        }""";
    Ticket ticket = new Ticket();
    JSONObject json = new JSONObject(jsonString);
    ticket.loadTicketFromJson(json);
    assertEquals(35436, ticket.getId());
    assertEquals("incident", ticket.getType());
    assertEquals("high", ticket.getPriority());
    assertEquals("open", ticket.getStatus());
    assertEquals("2009-07-20T22:55:29Z", ticket.getCreateDate());
    assertEquals("2011-05-05T10:38:52Z", ticket.getUpdateDate());
    assertEquals("20978392", ticket.getRequesterId());
    assertEquals("Help, my printer is on fire!", ticket.getSubject());
    assertEquals("The fire is very colorful.", ticket.getDescription());
  }


} 
