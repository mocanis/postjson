package com.example;

public class MyClass {
    /**
     *

    public static void main(String[] args) {
        try {
            String urlPath = new String("http://121.199.5.95:10010/alarm/saveAlarm.json");
            //String urlPath = new String("http://localhost:8080/Test1/HelloWorld?name=丁丁".getBytes("UTF-8"));
//            String param = "name=" + URLEncoder.encode("丁丁", "UTF-8");
            //建立连接
            URL url = new URL(urlPath);
            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
            //设置参数
            httpConn.setDoOutput(true); //需要输出
            httpConn.setDoInput(true); //需要输入
            httpConn.setUseCaches(false); //不允许缓存
            httpConn.setRequestMethod("POST"); //设置POST方式连接
            //设置请求属性
            httpConn.setRequestProperty("Content-Type", "application/json");
            httpConn.setRequestProperty("Connection", "Keep-Alive");// 维持长连接
            httpConn.setRequestProperty("Charset", "UTF-8");
            //连接,也可以不用明文connect，使用下面的httpConn.getOutputStream()会自动connect
            httpConn.connect();
            //建立输入流，向指向的URL传入参数
            DataOutputStream dos = new DataOutputStream(httpConn.getOutputStream());
            String jsons= "{\"sostype\":\"SOS_TYPE_SOS\",\"x\":\"120.0816748183\",\"y\":\"30.2733292576\",\"scene\":\"\",\"telphone\":\"135\",\"name\":\"[Terminal2]消防报警\",\"triggerdevice\":\"2\",\"alarmtime\":\"2016-12-01 16:32:04\"}";
            System.out.println(jsons);
            dos.writeBytes(jsons);
            System.out.println("write finish");
            dos.flush();
            dos.close();
            //获得响应状态
            int resultCode = httpConn.getResponseCode();
            if (HttpURLConnection.HTTP_OK == resultCode) {
                StringBuffer sb = new StringBuffer();
                String readLine = new String();
                BufferedReader responseReader = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), "UTF-8"));
                while ((readLine = responseReader.readLine()) != null) {
                    sb.append(readLine).append("\n");
                }
//                String resultString = inputStreamToString(httpConn.getInputStream());
//                System.out.println();
//                responseReader.close();
//                System.out.println(sb.toString());

            }


        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
     *
     */



}
