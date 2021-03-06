package com.fineapple.fineapple.data;

import android.content.Context;

import com.fineapple.fineapple.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by kksd0900 on 2017. 3. 24..
 */

public class SampleDataGenerator {
    private Context context;
    public ArrayList<HitObject> data;

    public SampleDataGenerator(Context mContext, int index) {
        this.context = mContext;
        data = new ArrayList<HitObject>();
        try {
            int file = 0;
            if (index == 1) {
                file = R.raw.hittestdata10_1;
            } else if (index == 2) {
                file = R.raw.hittestdata10_2;
            } else if (index == 3) {
                file = R.raw.hittestdata10_3;
            } else if (index == 4) {
                file = R.raw.hittestdata10_4;
            } else if (index == 5) {
                file = R.raw.hittestdata10_5;
            }
            readData(context, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void readData(Context context, int file) throws IOException {
        InputStream is = context.getResources().openRawResource(file);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        String mLine = reader.readLine();

        while (mLine != null) {
            mLine = reader.readLine();
            StringTokenizer parser = new StringTokenizer(mLine, ",");

            int index = 0;
            float x = 0, y = 0, z = 0;
            while(parser.hasMoreTokens()) {
                if (index == 5) {
                    x = Float.parseFloat(parser.nextToken());
                } else if (index == 6) {
                    y = Float.parseFloat(parser.nextToken());
                } else if (index == 7) {
                    z = Float.parseFloat(parser.nextToken());
                } else {
                    parser.nextToken();
                }
                index++;

            }
            if (data.size() < 4000) {
                HitObject hitObject = new HitObject(x, y, z);
                data.add(hitObject);
            }
//            Log.d("hansjin", hitObject.toString());
        }
        reader.close();
    }
}
