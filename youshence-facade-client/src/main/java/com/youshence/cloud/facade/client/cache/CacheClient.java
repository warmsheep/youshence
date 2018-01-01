package com.youshence.cloud.facade.client.cache;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.youshence.cloud.facade.client.cache.CacheClient.HystrixClientFallback;



@FeignClient(name = "youshence-service-cache", fallback = HystrixClientFallback.class )
public interface CacheClient {
	
	/**
	 * 获取缓存数据
	 * @return
	 */
	@RequestMapping(value = "/cache/getKvData", method = RequestMethod.POST)
	public String getKvData(@RequestParam(value="key",required = true)String key);
	
	/**
	 * 存入缓存数据
	 * @return
	 */
	@RequestMapping(value = "/cache/saveKvData", method = RequestMethod.POST)
	public void saveKvData(
			@RequestParam(value="key",required = true)String key,
			@RequestParam(value="value",required = true)String value,
			@RequestParam(value="timeout",required = false)Integer timeout
			);
	
	/**
	 * 删除缓存数据
	 * @return
	 */
	@RequestMapping(value = "/cache/deleteKvData", method = RequestMethod.POST)
	public void deleteKvData(
			@RequestParam(value="key",required = true) String key);
	
	/**
	 * 删除缓存数据
	 * @return
	 */
	@RequestMapping(value = "/cache/deleteKvDataList", method = RequestMethod.POST)
	public void deleteKvDataList(
			@RequestBody List<String> keyList
			);
	
	/**
	 * 检查缓存是否存在
	 * @return
	 */
	@RequestMapping(value = "/cache/hasKey", method = RequestMethod.POST)
	public String hasKey(@RequestParam(value="key",required = true) String key);
	
	
	/**
	 * 内部类-断路器
	 * @author warmsheep
	 *
	 */
	@Component
	static class HystrixClientFallback implements CacheClient{

		@Override
		public String getKvData(String key) {
			return "";
		}

		@Override
		public void saveKvData(String key, String value, Integer timeout) {
		}

		@Override
		public void deleteKvData(String key) {
		}

		@Override
		public void deleteKvDataList(List<String> keyList) {
		}

		@Override
		public String hasKey(String key) {
			return "";
		}
		
	}
}
