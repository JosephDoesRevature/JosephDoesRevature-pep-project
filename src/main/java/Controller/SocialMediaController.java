package Controller;

import io.javalin.Javalin;
import io.javalin.http.Context;

import Service.MessageService;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import Model.Account;
import Model.Message;
import Service.AccountService;
/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    
    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    MessageService messageService;
    AccountService accountService;
    public SocialMediaController(){
        messageService = new MessageService();
        accountService = new AccountService();
    }
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.get("example-endpoint", this::exampleHandler);
        app.post("/register", this::registerPost);
        app.post("/login", this::loginPost);
        app.post("/messages", this::messagesPost);
        app.get("/messages", this::messagesGet);
        app.get("/messages/{message_id}", this::messageGet);
        app.delete("/messages/{message_id}", this::messageDelete);
        app.patch("/messages/{message_id}", this::messagePatch);
        app.get("/accounts/{account_id}/messages", this::userMessagesGet);
        return app;
    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void exampleHandler(Context context) {
        context.json("sample text");
    }
    private void registerPost(Context context){
        ObjectMapper om = new ObjectMapper();
        String jsonString=context.body();
        Account account = null;
        try {
            account=om.readValue(jsonString, Account.class);            
        } catch (Exception e) {
            context.status(400);
        }
        account = accountService.registerAccount(account); 
        if(account != null){
            context.json(account);
        } else {
            context.status(400);
        }
    }
    private void loginPost(Context context){
        ObjectMapper om = new ObjectMapper();
        String jsonString=context.body();
        Account account = null;
        try {
            account=om.readValue(jsonString, Account.class);            
        } catch (Exception e) {
            context.status(401);
        }
        account = accountService.mockLogin(account); 
        if(account != null){
            context.json(account);
        } else {
            context.status(401);
        }
    }
    private void messagesPost(Context context){
        ObjectMapper om = new ObjectMapper();
        Message message = null;
        String jsonString = context.body();
        try {
            message = om.readValue(jsonString, Message.class);
            if(accountService.accountExists(message.posted_by)){
                message = messageService.makeMessage(message);
            } else {
                context.status(400);
                return; 
            }
        } catch (Exception e){
            context.status(400);
        }
        if(message != null){
            context.json(message);
        } else {
            context.status(400);
        }
    }
    private void messagesGet(Context context){
        context.json(this.messageService.getAllMessages());
    }
    private void messageGet(Context context){
        String mString = context.pathParam("message_id");
        int mID;
        try {
            mID = Integer.parseInt(mString);
         }
         catch (NumberFormatException e) {
            mID = -1;
         }
         Object message = this.messageService.getMessageByID(mID);
         if(message != null){
            context.json(message);
         }
    }
    private void messageDelete(Context context){
        String mString = context.pathParam("message_id");
        int mID;
        try {
            mID = Integer.parseInt(mString);
         }
         catch (NumberFormatException e) {
            mID = -1;
         }
         Object message = this.messageService.getMessageByID(mID);
         if(message != null){
            context.json(message);
            this.messageService.deleteMessageByID(mID);
         }
    }
    private void messagePatch(Context context){
        String mString = context.pathParam("message_id");
        int mID;
        try {
            mID = Integer.parseInt(mString);
         }
         catch (NumberFormatException e) {
            mID = -1;
         }
    }
    private void userMessagesGet(Context context){
        String aString = context.pathParam("account_id");
        int aID;
        try {
            aID = Integer.parseInt(aString);
         }
         catch (NumberFormatException e) {
            System.out.println(e.getMessage());
            return;
         }
         List messages = this.messageService.getMessagesByAccount(aID);
         context.json(messages);
    }

}