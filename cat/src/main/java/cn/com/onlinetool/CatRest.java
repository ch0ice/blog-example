package cn.com.onlinetool;

import com.dianping.cat.Cat;
import com.dianping.cat.message.Event;
import com.dianping.cat.message.Transaction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author choice
 * @description:
 * @date 2018/11/23 下午6:17
 *
 */
@RestController
public class CatRest {
    @GetMapping("testCat")
    public ResponseEntity testCat(){

        Transaction t = Cat.newTransaction("URL", "pageName");

        try {
            Cat.logEvent("URL.Server", "serverIp", Event.SUCCESS, "ip=${serverIp}");
            Cat.logMetricForCount("metric.key");
            Cat.logMetricForDuration("metric.key", 5);

            System.out.println("helloWorl");

            t.setStatus(Transaction.SUCCESS);
        } catch (Exception e) {
            t.setStatus(e);
            Cat.logError(e);
        } finally {
            t.complete();
        }


        return ResponseEntity.ok("ok");
    }
}
