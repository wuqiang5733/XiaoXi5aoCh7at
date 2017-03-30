package org.xuxiaoxiao.www.xiaoxiao;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by WuQiang on 2017/3/30.
 */

public class ChatMessageLab {

    private static ChatMessageLab chatMessageLab;

    public static ChatMessageLab get(Context context){
        if (chatMessageLab == null){
            chatMessageLab = new ChatMessageLab(context);
        }
        return chatMessageLab;
    }

    public ChatMessageLab(Context context){


    }

    public List<ChatMessage> getChatMessages(){
         List<ChatMessage> chatMessages = new ArrayList<>() ;

        for (int i =0 ;i < 100; i++){
            chatMessages.add (new ChatMessage(String.valueOf(i),"WQ" + i));
        }

        return chatMessages;
    }
}
/**
 *
 *     private static CrimeLab sCrimeLab;
 private Context mContext;
 private SQLiteDatabase mDatabase;

 public static CrimeLab get(Context context) {
 if (sCrimeLab == null) {
 sCrimeLab = new CrimeLab(context);
 }

 return sCrimeLab;
 }

 private CrimeLab(Context context) {
 mContext = context.getApplicationContext();
 mDatabase = new CrimeBaseHelper(mContext)
 .getWritableDatabase();

 }

 public void addCrime(Crime c) {
 ContentValues values = getContentValues(c);
 mDatabase.insert(CrimeTable.NAME, null, values);
 }

 public List<Crime> getCrimes() {
 List<Crime> crimes = new ArrayList<>();
 CrimeCursorWrapper cursor = queryCrimes(null, null);
 try {
 cursor.moveToFirst();
 while (!cursor.isAfterLast()) {
 crimes.add(cursor.getCrime());
 cursor.moveToNext();
 }
 } finally {
 cursor.close();
 }
 return crimes;
 }
 */
