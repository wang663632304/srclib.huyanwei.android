package srclib.huyanwei.phonelistener;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class BootCompletedReceiver extends BroadcastReceiver{

	private String TAG = "srclib.huyanwei.phonelistener.BootCompletedReceiver";
	@Override
	public void onReceive(Context context, Intent intent) {
		// TODO Auto-generated method stub
		 Log.v("TAG", "BootCompletedReceiver.onReceive()");
		 Intent svc = new Intent(context, PhoneListenerService.class);
		 //svc.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);//由于之前运行不能实现功能. declare @AndroidManifest.xml
		 context.startService(svc);
	}
}
