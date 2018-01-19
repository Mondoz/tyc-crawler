package com.hiekn.mongo;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.hiekn.util.ConstResource;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.ServerAddress;

/**
 * 
 * 里面维护一个MongoClient
 * 
 * @author pzn
 *
 */
public class MongoSingleton {
	
	/**
	 * log
	 */
	private static final Logger log = Logger.getLogger(MongoSingleton.class);
	
	/**
	 * mongo url
	 */
	private static final String MONGODB_URL = ConstResource.MONGO_URL;

	/**
	 * mongo port
	 */
	private static final int MONGODB_PORT = ConstResource.MONGO_PORT;

	/**
	 * mongo 重连次数
	 */
	private static final int MONGO_RETRY_TIMES = 5;
	
	
	//
	private MongoClient mgClient = null;

	public MongoSingleton() throws Exception {
		if (!init(MONGO_RETRY_TIMES)) {
			log.error("尝试 " + MONGO_RETRY_TIMES + " 次不能连上mongodb...");
			throw new Exception("failed connect to mongodb...");
		}
	}
	
	/**
	 * 
	 * 初始化mgClient、MongoDatabase、MongoCollection
	 * 
	 * 当设置tryTimes 大于 0  时，表示最大的尝试重连次数。如果尝试连接的次数
	 * 
	 * 等于tryTimes，还是不能成功初始化mgClient、MongoDatabase、MongoCollection.
	 * 
	 * 则会返回false，表示初始化失败，否则，返回true。
	 * 
	 * @param tryTimes 连接失败时，尝试连接的最大次数.必须大于等于1，否则不会初始化mongo
	 * @return false 初始化失败 , true 初始化成功
	 */
	private boolean init(int tryTimes) {

		if (tryTimes <= 0) {
			return false;
		}

		try {
			log.info("init mongo client ... start");
			MongoClientOptions options = MongoClientOptions.builder()
					.connectionsPerHost(15).minConnectionsPerHost(1)
					.maxConnectionIdleTime(3000).maxConnectionLifeTime(180000)
					.connectTimeout(10000).socketTimeout(100000).build();
			
			String[] urls = MONGODB_URL.split(",");
			List<ServerAddress> list = new ArrayList<ServerAddress>();
			for (String url : urls) {
				list.add(new ServerAddress(url, MONGODB_PORT));
			}
			
			mgClient = new MongoClient(list, options);
			log.info("init mongo client ... done");
			return true;
		} catch (Exception e) {
			log.error("Exception " + e);
			return init(--tryTimes);//尝试重新连接
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public MongoClient getMongoClient() {
		return mgClient;
	}
	
	/**
	 * 
	 */
	public void close() {
		log.info("close mongo client ... start");
		if (null != mgClient) {
			mgClient.close();
			mgClient = null;
		}
		log.info("close mongo client ... done");
	}
	
	/**
	 * 
	 * 
	 * db.sim_hash.aggregate(
	 *  	[
     * 			{
     * 				$group : {
     *     				_id : null,
     *     				total: { $sum: "$simi_count"},
     *     			count: { $sum: 1 }
     *  			}
     *			}
   	 *		]
     *	)  
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
}
