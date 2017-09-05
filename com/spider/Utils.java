package com.spider;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Utils {

    public static Map<String, String> getCookies() {
        String cookieStr = "_med=dw:1536&dh:864&pw:1920&ph:1080&ist:0; tk_trace=1; _tb_token_=3b69aef8e786; uc1=cookie14=UoTcC%2Bn1n7sZGQ%3D%3D&lng=zh_CN&cookie16=Vq8l%2BKCLySLZMFWHxqs8fwqnEw%3D%3D&existShop=false&cookie21=UIHiLt3xTIkz&tag=8&cookie15=UIHiLt3xD8xYTw%3D%3D&pas=0; uc3=sg2=AiKC2vxMI2dG3RdkAUrU3Q9DRjz2wPzYHdB7j%2BFlON8%3D&nk2=VyyWTGuQVA%3D%3D&id2=UoH63Bu8JkKlmg%3D%3D&vt3=F8dBzWfWWV5l92H32v4%3D&lg2=V32FPkk%2Fw0dUvg%3D%3D; uss=Bqblhsq5iKy7cyMG97Z37vjVvgAaIdX8tNadIXXbmU%2BjB%2FdQl3cqKWqXUCY%3D; lgc=407%5Cu8272%5Cu957F; tracknick=407%5Cu8272%5Cu957F; cookie2=1b8f5227595c05429f500cb45ccafff4; sg=%E9%95%BF30; cookie1=UR3d5gD1smPbCrfnct6BERrGFh5u2x%2FGq6VIvsh4Hlg%3D; unb=1056472463; t=a4f6a2b4b6c449c0ca14ef327e83d8aa; _l_g_=Ug%3D%3D; _nk_=407%5Cu8272%5Cu957F; cookie17=UoH63Bu8JkKlmg%3D%3D; login=true; cq=ccp%3D0; swfstore=257284; x=__ll%3D-1%26_ato%3D0; cna=5FDiEV5oMAsCAXjqOEJCSr/f; pnm_cku822=040UW5TcyMNYQwiAiwQRHhBfEF8QXtHcklnMWc%3D%7CUm5Ockp3TnZCfkt1THdLdiA%3D%7CU2xMHDJ7G2AHYg8hAS8UKQcnCVU0Uj5ZJ11zJXM%3D%7CVGhXd1llXWBZYVVpXGJbYFxhVmtJc0t0TnVMeE1wRHhDd0J7RWs9%7CVWldfS0TMw8xCj4eIQEvQisFUwU%3D%7CVmhIGCUFOBgkHCAUNA8xCDYWKhIsFzcCOQAgHCQaIQE0CzJkMg%3D%3D%7CV25Tbk5zU2xMcEl1VWtTaUlwJg%3D%3D; res=scroll%3A1519*6343-client%3A1519*734-offset%3A1519*6343-screen%3A1536*864; otherx=e%3D1%26p%3D*%26s%3D0%26c%3D0%26f%3D0%26g%3D0%26t%3D0; whl=-1%260%260%260; isg=Ajs7zndQ5uwGNNqH1AGpYpqnyh9lOAKYmoOTYy34FzpRjFtutWDf4lmO0hs4";
        HashMap<String ,String > map = new HashMap<String ,String >();
        String [] re = cookieStr.split(";");
        System.out.println(re.length);
        for (int i=0;i<re.length;i++){
            String[] temp = re[i].split("=");
            map.put(temp[0],temp[1]);
        }
        return map;
    }
}
