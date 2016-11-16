package cn.com.paiu.zk.utils;

import android.content.Context;
import android.media.AudioManager;
/**
 * --------------------------------------------------------
 * Date            Time    	    Author        		Version
 * --------------------------------------------------------
 * 2016/11/16     16:54     tangxin(Michael)        1.0
 * --------------------------------------------------------
 */
public class VolumnUtils {
    public static final int VOLUMN_NONE = 0; //静音
    public static final int VOLUMN_MAX = -1; //最大音量
    public static final int VOLUMN_MIDDLE = -2; //一半音量

    /**
     * 设置媒体音量
     * @param context
     * @param volumn  VOLUMN_NONE静音、 VOLUMN_MAX最大声音、 VOLUMN_MIDDLE、一半大  可以自行设大小int
     */
    public static void setVolume(Context context,int volumn){
        AudioManager audioManger= (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);

        if(volumn == VOLUMN_MAX){
            volumn = audioManger.getStreamMaxVolume(audioManger.STREAM_MUSIC);
        }else if(volumn == VOLUMN_MIDDLE){
            volumn = audioManger.getStreamMaxVolume(audioManger.STREAM_MUSIC);
            volumn = volumn / 2;
        }
        audioManger.setStreamVolume(AudioManager.STREAM_MUSIC,volumn, 0);
    }

    /**
     * 获取媒体音量
     * @param context
     * @return
     */
    public  static int getVolumn(Context context){
        AudioManager audioManger= (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
        return audioManger.getStreamVolume( AudioManager.STREAM_MUSIC );
    }
}
