package extend.faceEngineTest;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/JSONServlerDemo01")
public class ajaxTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ajaxTest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json; charset=utf-8");
        request.setCharacterEncoding("utf-8");
        PrintWriter out = response.getWriter();
        String a=request.getParameter("image");//��ȡajax�ϴ���base64�����ʽ��ͼƬ����
        Base64.Decoder decoder = Base64.getDecoder();//����ͼƬ�Ľ���
       
        byte[] b = decoder.decode(a);// �����쳣����
        for (int i = 0; i < b.length; ++i) {
            if (b[i] < 0) {
                b[i] += 256;
            }
        }
        OutputStream out1 = new FileOutputStream("d:\\testpic.jpg");//��ת����ͼƬ���浽ָ��λ��
        out1.write(b);
        out1.flush();
        out1.close();
        webcam c =new webcam();//����ʶ��ӿ�
        System.out.println(c.fun("D:\\aaa.jpg"));//������ƶȵ�ֵ������Ĳ������Ǵ����ݿ��ϻ�ȡ��ͼƬ�ĵ�ַ
	}

}
