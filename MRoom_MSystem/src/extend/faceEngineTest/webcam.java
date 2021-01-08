package extend.faceEngineTest;

import static com.arcsoft.face.toolkit.ImageFactory.getRGBData;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.arcsoft.face.ActiveFileInfo;
import com.arcsoft.face.EngineConfiguration;
import com.arcsoft.face.FaceEngine;
import com.arcsoft.face.FaceFeature;
import com.arcsoft.face.FaceInfo;
import com.arcsoft.face.FaceSimilar;
import com.arcsoft.face.FunctionConfiguration;
import com.arcsoft.face.enums.DetectMode;
import com.arcsoft.face.enums.DetectOrient;
import com.arcsoft.face.enums.ErrorInfo;
import com.arcsoft.face.toolkit.ImageInfo;

public class webcam {
	// 从官网获取
	String appId = "4dTQLDT1XXKegVCsZLdnb5EzwWQR6Qm4KWcG9MVBoA8v";
	 String sdkKey = "E4hpryAyCvCa6mq2oNAzAvxdTPPKs9Gg76CTtSLv9tgN";

	 FaceEngine faceEngine = new FaceEngine("D:\\arcsoft_lib");//加载依赖库，这里到时候根据自己电脑上存放的位置来改变
	// 激活引擎
	 int errorCode = faceEngine.activeOnline(appId, sdkKey);
	 public webcam() {
		 initFaceEngine();
		 System.out.println("初始化引擎");
		 
	 }

	 private  void initFaceEngine() {//初始化引擎，不需要改动
		
		if (errorCode != ErrorInfo.MOK.getValue() && errorCode != ErrorInfo.MERR_ASF_ALREADY_ACTIVATED.getValue()) {
			System.out.println("引擎激活失败");
		}

		ActiveFileInfo activeFileInfo = new ActiveFileInfo();
		errorCode = faceEngine.getActiveFileInfo(activeFileInfo);
		if (errorCode != ErrorInfo.MOK.getValue() && errorCode != ErrorInfo.MERR_ASF_ALREADY_ACTIVATED.getValue()) {
			System.out.println("获取激活文件信息失败");
		}

		// 引擎配置
		EngineConfiguration engineConfiguration = new EngineConfiguration();
		engineConfiguration.setDetectMode(DetectMode.ASF_DETECT_MODE_IMAGE);
		engineConfiguration.setDetectFaceOrientPriority(DetectOrient.ASF_OP_ALL_OUT);
		engineConfiguration.setDetectFaceMaxNum(10);
		engineConfiguration.setDetectFaceScaleVal(16);
		// 功能配置
		FunctionConfiguration functionConfiguration = new FunctionConfiguration();
		functionConfiguration.setSupportAge(true);
		functionConfiguration.setSupportFace3dAngle(true);
		functionConfiguration.setSupportFaceDetect(true);
		functionConfiguration.setSupportFaceRecognition(true);
		functionConfiguration.setSupportGender(true);
		functionConfiguration.setSupportLiveness(true);
		functionConfiguration.setSupportIRLiveness(true);
		engineConfiguration.setFunctionConfiguration(functionConfiguration);

		// 初始化引擎
		errorCode = faceEngine.init(engineConfiguration);

		if (errorCode != ErrorInfo.MOK.getValue()) {
			System.out.println("初始化引擎失败");
		}
	}

	public  double fun(String path) {//输入图片的位置来与指定图片对比
		
		ImageInfo imageInfo = getRGBData(new File(path));
		List<FaceInfo> faceInfoList = new ArrayList<FaceInfo>();
		errorCode = faceEngine.detectFaces(imageInfo.getImageData(), imageInfo.getWidth(), imageInfo.getHeight(),
				imageInfo.getImageFormat(), faceInfoList);

		// 特征提取
		FaceFeature faceFeature = new FaceFeature();
		errorCode = faceEngine.extractFaceFeature(imageInfo.getImageData(), imageInfo.getWidth(), imageInfo.getHeight(),
				imageInfo.getImageFormat(), faceInfoList.get(0), faceFeature);

		// 人脸检测2
		ImageInfo imageInfo2 = getRGBData(new File("D:\\testpic.jpg"));//这里也是根据自己存放位置改变（web上拍的那个照片这是这张）
		List<FaceInfo> faceInfoList2 = new ArrayList<FaceInfo>();
		errorCode = faceEngine.detectFaces(imageInfo2.getImageData(), imageInfo2.getWidth(), imageInfo2.getHeight(),
				imageInfo.getImageFormat(), faceInfoList2);

		// 特征提取2
		FaceFeature faceFeature2 = new FaceFeature();
		errorCode = faceEngine.extractFaceFeature(imageInfo2.getImageData(), imageInfo2.getWidth(),
				imageInfo2.getHeight(), imageInfo.getImageFormat(), faceInfoList2.get(0), faceFeature2);

		// 特征比对
		FaceFeature targetFaceFeature = new FaceFeature();
		targetFaceFeature.setFeatureData(faceFeature.getFeatureData());
		FaceFeature sourceFaceFeature = new FaceFeature();
		sourceFaceFeature.setFeatureData(faceFeature2.getFeatureData());
		FaceSimilar faceSimilar = new FaceSimilar();

		errorCode = faceEngine.compareFaceFeature(targetFaceFeature, sourceFaceFeature, faceSimilar);

		return faceSimilar.getScore();
	}
}