package jpgToPng;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;

public class Main
{
	private final static int TARGET_WIDTH = 200;
	private final static int TARGET_HEIGHT = 220;

	public static void main(String[] args)
	{
		try
		{
			Class<Main> clazz = Main.class;
			// ��ȡJPG�ļ�����·��
			String jpgFilePath = clazz.getResource("image.jpg").getPath();
			System.out.println(jpgFilePath);
			// ��ȡJPG�ļ�����Ŀ¼
			String fileDir = jpgFilePath.substring(0, jpgFilePath.lastIndexOf("/"));
			System.out.println(fileDir);

			File jpgFile = new File(jpgFilePath);
			BufferedImage jpgImage = ImageIO.read(jpgFile);
			int targetWidth = TARGET_WIDTH;
			int targetHeight = TARGET_HEIGHT;

			// ����PNG�ļ���JPG�ļ����رȣ�ȡ�ߺͿ��н�С�����ر�������PNG�ļ�������
			double scaleWidth = (double) 200 / jpgImage.getWidth();
			double scaleHeight = (double) 200 / jpgImage.getHeight();
			if (scaleWidth > scaleHeight)
			{
				scaleWidth = scaleHeight;
				targetWidth = (int) (scaleWidth * jpgImage.getWidth());
			}
			else
			{
				scaleHeight = scaleWidth;
				targetHeight = (int) (scaleHeight * jpgImage.getHeight());
			}
			BufferedImage pngImage = new BufferedImage(targetWidth, targetHeight, jpgImage.getType());

			Graphics2D graphics = pngImage.createGraphics();
			graphics.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
			graphics.drawRenderedImage(jpgImage, AffineTransform.getScaleInstance(scaleWidth, scaleHeight));
			graphics.dispose();

			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			ImageIO.write(pngImage, "png", byteArrayOutputStream);
			File pngFile = new File(fileDir + "/image.png");
			// ���PNG�ļ��Ѿ����ڣ���ɾ��
			if (pngFile.exists())
				pngFile.delete();
			// ����PNG�ļ�
			FileUtils.writeByteArrayToFile(pngFile, byteArrayOutputStream.toByteArray());
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			System.out.println("finish translate");
		}
	}
}
