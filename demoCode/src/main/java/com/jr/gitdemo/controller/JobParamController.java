package com.jr.gitdemo.controller;



import com.jr.gitdemo.common.DataTablePageResult;
import com.jr.gitdemo.common.ResponseModel;
import com.jr.gitdemo.common.ServiceException;
import com.jr.gitdemo.entity.TaskInfo;
import com.jr.gitdemo.service.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author wujiangwei
 * @date 2019/5/15 14:01
 */
@Slf4j
@RestController
@RequestMapping(value = "Job")

public class JobParamController {
    @Autowired
    TaskService taskService;

    @RequestMapping("/test")
    @ResponseBody
    public String print()
    {
        return "testAAA";
    }

//    Util util = new Util();

//    @GetMapping("jobInfo")
//    @CrossOrigin
//    public Map<String, Object> jobInfo(@RequestParam(value = "jobName",defaultValue = "") String  jobName,
//                                       @RequestParam(value = "start", required = true, defaultValue = "0") Integer start,
//                                       @RequestParam(value = "length", required = true, defaultValue = "10") Integer length){
//        ResponseModel responseModel = new ResponseModel();
//
//        int startPage = start == 0 ? 1 : (start / length + 1);
//        TaskInfo taskInfo = new TaskInfo();
//        taskInfo.setJobName(jobName);
//        List<TaskInfo> list = taskService.list(taskInfo);
//
//        if(list != null){
//            //找到partners对应中文名并且返回
//            for(int i = 0; i < list.size(); i++){
//                String partners = list.get(i).getJobDataMap().getString("partners");
//                String partnersName = "";
//                if(partners != null){
//                    String partnerArr[] = partners.split(",");
//                    for(int j = 0; j < partnerArr.length; j++){
//                        String partner = partnerArr[j];
//                        if(util.isPingAnPay(partner)){
//                            partnersName += "老河口,";
//                        }else if(util.isZhaoShangPay(partner)){
//                            partnersName += "山西,";
//                        }
//                    }
//                    if(partnersName.length() > 0){
//                        partnersName = partnersName.substring(0,partnersName.length() - 1);
//                    }
//                }
//                list.get(i).setBelong(partnersName);
//
//            }
//        }


//        // 组装dataTable数据
//        DataTablePageResult<TaskInfo> result = new DataTablePageResult<TaskInfo>();
//        result.setData(list);
//        result.setTotal(0L);
//        result.setFiltered(0L);
//        return result.getResult();
//    }

//    @ApiOperation(nickname = "addJob", value = "添加Job")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "taskInfo", paramType = "body", dataType = "TaskInfo", value = "任务信息")
//    })
    @GetMapping("addJob")
    @CrossOrigin
//    @RequestBody TaskInfo taskInfo
    public ResponseModel addJob(){

        ResponseModel responseModel = new ResponseModel();

        try {
//            taskService.addJob(taskInfo);
            taskService.addJob();

        } catch (Exception e) {
            responseModel.setStatus(false);
            responseModel.setMessage(e.getMessage());
            return responseModel;
        }
        responseModel.setStatus(true);
        responseModel.setMessage("操作成功");
        return responseModel;
    }

//    @ApiOperation(nickname = "editJob", value = "修改Job")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "taskInfo", paramType = "body", dataType = "TaskInfo", value = "任务信息")
//    })
    @PutMapping("editJob")
    @CrossOrigin
    public ResponseModel editJob(@RequestBody TaskInfo taskInfo){
        ResponseModel responseModel = new ResponseModel();
        try {
            taskService.edit(taskInfo);
        } catch (Exception e) {
            responseModel.setStatus(false);
            responseModel.setMessage(e.getMessage());
            return responseModel;
        }
        responseModel.setStatus(true);
        responseModel.setMessage("操作成功");
        return responseModel;
    }

//    @ApiOperation(nickname = "delJob", value = "删除Job")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "jobName", paramType = "query", dataType = "String", value = "任务名信息"),
//            @ApiImplicitParam(name = "jobGroup", paramType = "query", dataType = "String", value = "任务组信息")
//    })
    @DeleteMapping("delJob")
    @CrossOrigin
    public ResponseModel delJob(@RequestParam("jobName") String jobName,@RequestParam("jobGroup") String jobGroup){
        ResponseModel responseModel = new ResponseModel();
        try {
            taskService.delete(jobName, jobGroup);
        } catch (Exception e) {
            responseModel.setStatus(false);
            responseModel.setMessage(e.getMessage());
            return responseModel;
        }
        responseModel.setStatus(true);
        responseModel.setMessage("操作成功");
        return responseModel;
    }

//    @ApiOperation(nickname = "pauseJob", value = "暂停Job")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "jobName", paramType = "query", dataType = "String", value = "任务名信息"),
//            @ApiImplicitParam(name = "jobGroup", paramType = "query", dataType = "String", value = "任务组信息")
//    })
    @GetMapping("pauseJob")
    @CrossOrigin
    public ResponseModel pauseJob(@RequestParam("jobName") String jobName,@RequestParam("jobGroup") String jobGroup){
        ResponseModel responseModel = new ResponseModel();
        try {
            taskService.pause(jobName, jobGroup);
        } catch (Exception e) {
            responseModel.setStatus(false);
            responseModel.setMessage(e.getMessage());
            return responseModel;
        }
        responseModel.setStatus(true);
        responseModel.setMessage("操作成功");
        return responseModel;
    }

//    @ApiOperation(nickname = "resumeJob", value = "重启Job")
//    @ApiImplicitParams({
//            @ApiImplicitParam(name = "jobName", paramType = "query", dataType = "String", value = "任务名信息"),
//            @ApiImplicitParam(name = "jobGroup", paramType = "query", dataType = "String", value = "任务组信息")
//    })
    @GetMapping("resumeJob")
    @CrossOrigin
    public ResponseModel resumeJob(@RequestParam("jobName") String jobName,@RequestParam("jobGroup") String jobGroup){
        ResponseModel responseModel = new ResponseModel();
        try {
            taskService.resume(jobName, jobGroup);
        } catch (Exception e) {
            responseModel.setStatus(false);
            responseModel.setMessage(e.getMessage());
            return responseModel;
        }
        responseModel.setStatus(true);
        responseModel.setMessage("操作成功");
        return responseModel;
    }
}
