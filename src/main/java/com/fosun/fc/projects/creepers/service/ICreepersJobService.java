package com.fosun.fc.projects.creepers.service;

import java.util.List;
import java.util.Map;

import org.quartz.SchedulerException;
import org.springframework.data.domain.Page;

import com.fosun.fc.projects.creepers.dto.CreepersJobDTO;

public interface ICreepersJobService{

	/**     
	 * 获取单个任务     
	 * @param jobName     
	 * @param jobGroup     
	 * @return     
	 * @throws SchedulerException     
	 */
	CreepersJobDTO getJob(String jobName, String jobGroup) throws SchedulerException;

	/**     
	 * 获取所有任务     
	 * @return     
	 * @throws SchedulerException     
	 */
	List<CreepersJobDTO> getAllJobs() throws SchedulerException;

	/**     
	 * 所有正在运行的job     
	 *      
	 * @return     
	 * @throws SchedulerException     
	 */
	List<CreepersJobDTO> getRunningJob() throws SchedulerException;

	/**     
	 * 查询任务列表     
	 * @return     
	 */
	List<CreepersJobDTO> getTaskList();

	/**     
	 * 添加任务     
	 *      
	 * @param scheduleJob     
	 * @throws SchedulerException     
	 */
	boolean addJob(CreepersJobDTO job) throws SchedulerException;

	/**     
	 * 暂停任务     
	 * @param scheduleJob     
	 * @return     
	 */
	boolean pauseJob(CreepersJobDTO scheduleJob);

	/**     
	 * 恢复任务     
	 * @param scheduleJob     
	 * @return     
	 */
	boolean resumeJob(CreepersJobDTO scheduleJob);

	/**     
	 * 删除任务     
	 */
	boolean deleteJob(CreepersJobDTO scheduleJob);

	/**     
	 * 立即执行一个任务     
	 * @param scheduleJob     
	 * @throws SchedulerException     
	 */
	void testJob(CreepersJobDTO scheduleJob) throws SchedulerException;

	/**     
	 * 更新任务时间表达式     
	 * @param scheduleJob     
	 * @throws SchedulerException     
	 */
	void updateCronExpression(CreepersJobDTO scheduleJob) throws SchedulerException;
	
	/**     
	 * 任务List分页查询  
	 * @throws Exception     
	 */	
	public Page<?> findList(Map<String, Object> searchParams, int pageNumber, int pageSize, String sortType) throws Exception;

	/**     
     * 获取单个任务     
     * @param jobName     
     * @return CreepersJobDTO
     */
    public CreepersJobDTO findJob(String jobName);
    
    /**     
     * 更新任务:通过任务名称更新任务
     * @param jobName     
     * @param request     
     * @return CreepersJobDTO
     */
    public void updateResumeRequestByJobName(String jobName,String request);
}