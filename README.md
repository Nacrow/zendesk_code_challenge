# Zendesk Ticket Viewer

It is a Ticket Viewer based on tickets management tools from zendesk.

## Description

This application includes 2 functions:

1. Request and list all tickets for the account with pagination
2. Display individual ticket details.

## Installation

### 1. Preparation

- Make sure JAVA is installed in your machine
- Unzip the TicketView.zip.

### 2. Add email address, token, and subdomain to access the service.

Please add the email address, token, and subdomain to the information.properties file located in `./information.properties`.

Here is an example of modified information.properties:
(This example does not contain any true token or email address)

```
subdomain=zccstudent

email=abc@abc.com

token=83498jdksfhjr3i392asoIlF
```

### 3. Run the application

Using the following command to run the jar JAR file

```shell
java -jar zendesk.jar
```

## Usage

### 1. Display all tickets

This application will load or reload all tickets in a list when the user requests to display all tickets. Tickets will be listed with a number at the beginning of the line. Long subject (more than 60) will be abbreviated automatically.

Enter `menu` -> `1` to display all tickets.

output Example:

```
1    |Subject:Sample ticket: Meet the ticket                              |Requester ID:422099977351|Create Date:2021-11-23T01:28:50Z
2    |Subject:Test ticket..............Test ticket..............Test ti...|Requester ID:421918360752|Create Date:2021-11-24T05:58:59Z
```

### 2. Pagination

If there are more than 25 tickets to list, 25 tickets will be listed per page. Enter `p` to go to the previous page, enter `n` to go to the next page, and enter `r` to return to the menu.

### 3. Display individual ticket details

After returning to the menu, enter `2` to display the individual ticket details. Then, the user should type the number at the beginning of that ticket in the list.

For example, for the ticket list shown in the previous section, enter `1` to display the ticket with the subject `Sample ticket: Meet the ticket `and enter `2`to view the ticket with the subject`Test ticket.............. Test ticket..............Test ti...`

Ticket List:

```
1    |Subject:Sample ticket: Meet the ticket                              |Requester ID:422099977351|Create Date:2021-11-23T01:28:50Z
2    |Subject:Test ticket..............Test ticket..............Test ti...|Requester ID:421918360752|Create Date:2021-11-24T05:58:59Z
```

Enter 1:

```
1 Subject: Sample ticket: Meet the ticket
___________________________
Hi there,

I’m sending an email because I’m having a problem setting up your new product. Can you help me troubleshoot?

Thanks,
 The Customer


___________________________
Requester ID:000000000000
Priority:normal | Status:open | Type:incident
Tags:zendesk sample support 
Create Date:2021-11-23T01:28:50Z | Update Date:2021-11-23T01:28:51Z
```

### 4. Quit

Enter `quit` to exit.