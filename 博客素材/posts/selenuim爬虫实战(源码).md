>SuperLOFTERDownloader7.java

```
package test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;

public class SuperLOFTERDownloader7 {
	// static String html;
	static int urlCount = 0;
	static String username = "fenchenyue";
	static String url = "http://" + username + ".lofter.com/view";
	static String picUrl;
	static boolean ifToBreak = false;
	static ArrayList<String> urlList = new ArrayList<String>();
	// 注意:目录路径必须以"\"结尾
	static String downloadToDir = "E:\\爬虫\\妹纸2\\";

	public static void main(String[] args) {
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\Jim\\Desktop\\GeckoDriver\\geckodriver.exe");

		FirefoxOptions options = new FirefoxOptions();

		// 启动配置"不加载图片"
		options.addPreference("permissions.default.image", 2);

		// 启动参数"无界面"
		FirefoxBinary myBinary = new FirefoxBinary();
		myBinary.addCommandLineOptions("--headless");
		options.setBinary(myBinary);

		FirefoxDriver driver = new FirefoxDriver(options);
		driver.manage().timeouts().implicitlyWait(16, TimeUnit.SECONDS); // 隐式等待
		driver.get(url);

		// ((JavascriptExecutor)
		// driver).executeScript("document.querySelector('.m-txtsch').style.display=\"inline\"");

		int givenNumber = Integer.parseInt(driver.findElementByCssSelector(
				"body > div.g-bdfull.g-bdfull-show.ztag > div.g-bdc.ztag > div.m-fbar.f-cb > div.schbtn.f-cb > div:nth-child(1) > div > div.txt > a.ztag.currt > span")
				.getAttribute("innerHTML"));

		Actions action = new Actions(driver);

		Timer timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				action.sendKeys(/* driver.findElement(By.cssSelector("body")), */ Keys.END).perform();
			}
		}, 1, 700);

		/*
		 * WebDriverWait wait = new WebDriverWait(driver, 256, 2048);
		 * wait.until(ExpectedConditions .numberOfElementsToBe(By.
		 * cssSelector("div.ztag > div.m-filecnt.m-filecnt-1 > ul > li"), number));
		 */

		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				int flag = JOptionPane.showConfirmDialog(null, "正在收集资源...\n如果觉得时间过长,点击'是'提前中断", "是否中断",
						JOptionPane.YES_NO_OPTION);
				if (flag == JOptionPane.YES_OPTION) {
					ifToBreak = true;
				}
			}
		}).start();

		// 周期查询文档是否加载完或者用户选择中断
		String js = "let count=0;document.querySelectorAll('.g-bdc > div:nth-child(3) > div.m-filecnt.m-filecnt-1 > ul').forEach(function(e,i){count+=e.children.length});return count;";
		while (true) {

			long countFromJs = (long) ((JavascriptExecutor) driver).executeScript(js);
			System.out.println("已收集到资源数 : " + countFromJs);
			if (Math.abs((int) countFromJs - givenNumber) < 5 || ifToBreak) {
				break;
			}
			try {
				Thread.sleep(1600);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		timer.cancel();

		for (WebElement element : driver.findElements(
				By.cssSelector("div.ztag > div.m-filecnt.m-filecnt-1 > ul > li > a > div > div > img.realimg"))) {
			picUrl = element.getAttribute("src").split("\\?")[0];
			System.out.println(picUrl);
			urlList.add(picUrl);
			urlCount++;

		}

		System.out.println("爬取到" + givenNumber + "篇文章中的" + urlCount + "张图的url");

		int flag = JOptionPane.showConfirmDialog(null, "核对url,是否开始下载?", "是否继续", JOptionPane.YES_NO_OPTION);
		if (flag == JOptionPane.NO_OPTION) {
			System.exit(0);
		} else if (flag == JOptionPane.YES_OPTION) {
			System.out.println("正在下载.....");
			try {
				Runtime.getRuntime().exec("explorer " + downloadToDir);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("完成,成功下载了" + DownloadFromUrlList3.download(urlList, downloadToDir) + "张图");
			System.out.println("失败数:" + DownloadFromUrlList3.errCount);
		}
		// JOptionPane.showMessageDialog(null, "浏览器可以关闭了吗?");
		driver.quit();
		System.exit(0);
	}
}
```
>DownloadFromUrlList3.java

```
package test;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class DownloadFromUrlList3 {
	static private int count = 0;
	static ThreadGroup myThreadGroup = new ThreadGroup("myGroup");
	static picThread myThread;
	public static int errCount = 0;
	static long timeOut = 8 * 1000;

	public static synchronized int updateCount() {
		count++;
		return count;
	};

	public static int download(ArrayList<String> urlList, String dirPath) {
		if (new File(dirPath).exists()) {
			if (!new File(dirPath).isDirectory()) {
				System.out.println("ERROR!! THE PATH GIVEN ISN'T A DIRECTORY");
				return 0;
			}
		} else {
			new File(dirPath).mkdir();
		}

		for (String urlstr : urlList) {
			String myUrlStr = urlstr;
			myThread = new picThread(myThreadGroup, new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					try {
						URL url = new URL(myUrlStr);
						BufferedInputStream is;
						try {
							// 网络流量一定要用高效的buffered
							is = new BufferedInputStream(url.openStream());
						} catch (Exception e) {
							// TODO Auto-generated catch block
							System.out.println("发现一个url资源出错:\n" + myUrlStr);
							errCount++;
							return;
						}
						String extention = "." + HttpURLConnection.guessContentTypeFromStream(is).split("/")[1];
						File file = new File(dirPath + updateCount() + extention);
						((picThread) Thread.currentThread()).file = file;
						file.createNewFile();
						OutputStream os = new FileOutputStream(file);
						int len;
						byte[] buffer = new byte[1024];
						while ((len = is.read(buffer)) != -1) {
							os.write(buffer, 0, len);
						}
						is.close();
						os.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			});
			myThread.urlStr = myUrlStr;
			myThread.start();
			// threadList.add(myThread);
			try {
				myThread.join(timeOut);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		/*
		 * for (Thread iThread : threadList) { try { // 等待所有线程执行完毕 iThread.join(1000 *
		 * 15); } catch (InterruptedException e) { e.printStackTrace(); } }
		 */
		try {
			Thread.sleep(1024);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Thread[] activeThreads = new Thread[myThreadGroup.activeCount()];
		myThreadGroup.enumerate(activeThreads);
		for (Thread thread : activeThreads) {
			System.out.println("一张图片下载超时 :" + ((picThread) thread).file.getName() + "\n" + ((picThread) thread).urlStr);
			errCount++;
			// ((picThread) thread).file.delete();
		}
		return count - myThreadGroup.activeCount();
	}

	// 使用示例:
	public static void main(String[] args) {
		ArrayList<String> list = new ArrayList<String>();
		list.add(
				"http://imglf1.nosdn.127.net/img/WnhZUEZYSVNPd1lsd1doNjVPSWVkREFwNTRrNTdUM2tQYkk2bHVabHpIYjJOZVFFeEdaUWRnPT0.jpg");
		list.add("http://imgsrc.baidu.com/imgad/pic/item/b58f8c5494eef01f2c2e59feebfe9925bc317dd6.jpg");
		list.add(
				"http://imglf1.nosdn.127.net/img/WnhZUEZYSVNPd1p2Wk54NlRNMTZKLzJnci9HanJsbUNFUTlJdWdFaDhJQUlQQ3h5Y1kzOFlRPT0.jpg");
		list.add("http://b.hiphotos.baidu.com/image/pic/item/7a899e510fb30f247b237cc9c195d143ac4b03ba.jpg");
		System.out.println("完成,成功下载了:" + download(list, "C:\\Users\\Jim\\Desktop\\testPic\\"));
		System.out.println("失败:" + DownloadFromUrlList3.errCount);
		System.exit(0);
	}

}

class picThread extends Thread {
	public String urlStr = "";
	public File file;
	/*
	 * public void setUrl(String url) { urlStr = url; }
	 * 
	 * public String getUrl() { return urlStr; }
	 */

	public picThread(ThreadGroup group, Runnable run) {
		super(group, run);
	}
}
```