/*
 * Copyright 2015 Space Dynamics Laboratory - Utah State University Research Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package edu.usu.sdl.openstorefront.web.rest.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author dshurtleff
 */
public class ApplicationStatus
{

	private String applicationVersion;
	private int processorCount;
	private double systemLoad;
	private Map<String, String> systemProperties = new HashMap<>();
	private Date startTime;
	private String upTime;
	private long totalThreadCount;
	private long liveThreadCount;
	private String heapMemory;
	private String nonHeapMemory;
	private List<String> memoryPoolInfos = new ArrayList<>();
	private List<String> garbageCollectionInfos = new ArrayList<>();

	public ApplicationStatus()
	{
	}

	public String getApplicationVersion()
	{
		return applicationVersion;
	}

	public void setApplicationVersion(String applicationVersion)
	{
		this.applicationVersion = applicationVersion;
	}

	public int getProcessorCount()
	{
		return processorCount;
	}

	public void setProcessorCount(int processorCount)
	{
		this.processorCount = processorCount;
	}

	public double getSystemLoad()
	{
		return systemLoad;
	}

	public void setSystemLoad(double systemLoad)
	{
		this.systemLoad = systemLoad;
	}

	public Map<String, String> getSystemProperties()
	{
		return systemProperties;
	}

	public void setSystemProperties(Map<String, String> systemProperties)
	{
		this.systemProperties = systemProperties;
	}

	public Date getStartTime()
	{
		return startTime;
	}

	public void setStartTime(Date startTime)
	{
		this.startTime = startTime;
	}

	public String getUpTime()
	{
		return upTime;
	}

	public void setUpTime(String upTime)
	{
		this.upTime = upTime;
	}

	public long getTotalThreadCount()
	{
		return totalThreadCount;
	}

	public void setTotalThreadCount(long totalThreadCount)
	{
		this.totalThreadCount = totalThreadCount;
	}

	public long getLiveThreadCount()
	{
		return liveThreadCount;
	}

	public void setLiveThreadCount(long liveThreadCount)
	{
		this.liveThreadCount = liveThreadCount;
	}

	public String getHeapMemory()
	{
		return heapMemory;
	}

	public void setHeapMemory(String heapMemory)
	{
		this.heapMemory = heapMemory;
	}

	public String getNonHeapMemory()
	{
		return nonHeapMemory;
	}

	public void setNonHeapMemory(String nonHeapMemory)
	{
		this.nonHeapMemory = nonHeapMemory;
	}

	public List<String> getMemoryPoolInfos()
	{
		return memoryPoolInfos;
	}

	public void setMemoryPoolInfos(List<String> memoryPoolInfos)
	{
		this.memoryPoolInfos = memoryPoolInfos;
	}

	public List<String> getGarbageCollectionInfos()
	{
		return garbageCollectionInfos;
	}

	public void setGarbageCollectionInfos(List<String> garbageCollectionInfos)
	{
		this.garbageCollectionInfos = garbageCollectionInfos;
	}

}
