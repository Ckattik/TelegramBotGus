package org.example;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.send.SendAnimation;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.io.File;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class Main extends TelegramLongPollingBot {

    /*
    *    + Привіт !
    *    + [КНОПКА]Слава Україні!
    *
    *     Героям Слава!
    *    [КНОПКА] Слава Нації
    *
    *
    * */


    public static void main(String[] args) throws TelegramApiException {

        TelegramBotsApi api = new TelegramBotsApi(DefaultBotSession.class);
        api.registerBot(new Main());

    }
    @Override
    public String getBotUsername() {
        return "BanderogusCkattBot";
    }
    @Override
    public String getBotToken() {
        return "6290456327:AAGAbnU8g0AJ7hA5Kz6Q2pWBGTXBBIIoMtI";
    }
    @Override

    public void onUpdateReceived(Update update){
        Long chatId = getChatId(update);

        if(update.hasMessage() && update.getMessage().getText().equals("/start")){

            sendImage("level-1", chatId);
        }

        /*if (update.hasCallbackQuery()){
            if (update.getCallbackQuery().getData().equals("glory_for_ukraine ")) {
                   SendMessage message = createMessage("Героям Слава!");
                        message.setChatId(chatId);
                        sendApiMethodAsync(message);
            }else if (update.getCallbackQuery().getData().equals("nation_for_ukraine")){
                SendMessage message = createMessage("Смерть ворогам!");
                message.setChatId(chatId);
                sendApiMethodAsync(message);
            }else if (update.getCallbackQuery().getData().equals("huilo")){
                SendMessage message = createMessage("Хуйло , ла ла ла ла !");
                message.setChatId(chatId);
                sendApiMethodAsync(message);
            }
        }*/



    }
    /*public void onUpdateReceived(Update update) {
        Long chatId = getChatId(update);

        SendMessage msg = createMessage("*Hello* Vitaliy");
        attachButtons(msg, Map.of(
                "BTN_1", "hello_btn_1",
                "BTN_2", "hello_btn_2"


        ));
        msg.setChatId(chatId);
        sendApiMethodAsync(msg);
    }*/





    public Long getChatId(Update update){
        if(update.hasMessage()){
            return update.getMessage().getFrom().getId();
        }
        if (update.hasCallbackQuery()){
            return update.getCallbackQuery().getFrom().getId();
        }
        return null;
    }
    public SendMessage createMessage(String text){
       SendMessage message = new SendMessage();
       message.setText(new String(text.getBytes(), StandardCharsets.UTF_8));
       message.setParseMode("markdown");
       return message;
   }

   public void attachButtons(SendMessage message, Map<String,String> buttons){
       InlineKeyboardMarkup markup = new InlineKeyboardMarkup();

       List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
       for(String buttonName : buttons.keySet()){
           String buttonValue = buttons.get(buttonName);

           InlineKeyboardButton button = new InlineKeyboardButton();
           button.setText(new String(buttonName.getBytes(), StandardCharsets.UTF_8));
           button.setCallbackData(buttonValue);
           keyboard.add(Arrays.asList(button));


       }

       markup.setKeyboard(keyboard);
       message.setReplyMarkup(markup);
   }

     public void sendImage(String name, Long chatId ){
         SendAnimation animation = new SendAnimation();

         InputFile inputFile = new InputFile();
         inputFile.setMedia(new File("images/" + name + ".gif"));

         animation.setAnimation(inputFile);
         animation.setChatId(chatId);

         executeAsync(animation);
     }




}