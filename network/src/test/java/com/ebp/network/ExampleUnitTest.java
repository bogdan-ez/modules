package com.ebp.network;

import com.ebp.network.callback.ListOfObjectsCallback;
import com.ebp.network.model.Store;
import com.google.gson.Gson;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        ListOfObjectsCallback callback = new ListOfObjectsCallback() {
            @Override
            public void onSuccess(List objects) {

            }
        };

        String result = "[{\"_id\":\"5b3dcce226e280004a53c9a3\",\"updated_at\":\"2018-07-17T15:18:23.140Z\",\"created_at\":\"2018-07-05T07:46:42.538Z\",\"storeName\":\"Test Store\",\"storeImageUri\":\"http://res.cloudinary.com/dwhz3ryx6/image/upload/v1530890321/jlncevm5i73jucmghsx1.jpg\",\"latitude\":50.3952669,\"longitude\":30.6496748,\"storeAddress\":\"Vyshniakivska St, 8, Kyiv, Ukraine, 02000\",\"retailerId\":\"5b3dcce226e280004a53c9a2\",\"storeCategory\":\"Best category\",\"storePhone\":\"12345678901234567890\",\"openTill\":\"10:00 pm\",\"openFrom\":\"1:00 am\",\"activeItemsCount\":4,\"distanceToStore\":1.5},{\"_id\":\"5b3cd86626e280004a53c97e\",\"updated_at\":\"2018-07-08T11:02:53.005Z\",\"created_at\":\"2018-07-04T14:23:34.131Z\",\"storeName\":\"ebodya\",\"storeImageUri\":\"http://res.cloudinary.com/dwhz3ryx6/image/upload/v1530714204/mj9nszedzyhfa1ogricl.jpg\",\"latitude\":50.39369,\"longitude\":30.618462,\"storeAddress\":\"Chavdar Elizavety Street 1 Kyiv, 02140, Ukraine, 02000\",\"retailerId\":\"5b3cd86626e280004a53c97d\",\"openTill\":\"9:00 pm\",\"openFrom\":\"9:00 am\",\"activeItemsCount\":0,\"distanceToStore\":0.4}]";

        new Gson().fromJson(result, Store[].class);
        callback.onSuccess(Arrays.asList(new Gson().fromJson(result, Store[].class)));
        assertEquals(4, 2 + 2);
    }
}