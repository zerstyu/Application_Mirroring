# Application Mirroring
This is : 
* Application Mirroring. Based on Android Auto of Google and Carplay of Apple.
* Not full-mirroring.
* It operates in the background. So, it does not stop to interrupt.

※ interrupt ? call, message, home button and so on.

Server
* 1. get Bitmap using getDrawingCache(). 
* 2. Bitmap to Byte[].
* 3. Transfer Byte[] to Client. Byte[] is included message queue.


Client
* 1. Receive data from Server.
* 2. Received data to Byte[]. 

※  Received data is stored in the queue. Because it(Byte[] to Bitmap) takes a lot of time.
* 3. Byte[] to Bitmap.
* 4. set bitmap on ImageView.

However :
* It's not implemented layout.

# Feature
* Application Mirroring.
* It does not require rooting.
* Interactive touch.
* Display Capture.
* Display Rendering.
* Wireless Communication.


# Image
![alt tag](https://github.com/zerstyu/Application_Mirroring/blob/master/appmirroring1.PNG)
![alt tag](https://github.com/zerstyu/Application_Mirroring/blob/master/appmirroring2.PNG)
![alt tag](https://github.com/zerstyu/Application_Mirroring/blob/master/appmirroring3.PNG)
![alt tag](https://github.com/zerstyu/Application_Mirroring/blob/master/appmirroring4.PNG)
