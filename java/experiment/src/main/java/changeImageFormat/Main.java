package changeImageFormat;

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
			String jpgFilePath = clazz.getResource("image.png").getPath();
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
			// ImageIO.write(pngImage, "jpeg", byteArrayOutputStream);
			// File pngFile = new File(fileDir + "/image.jpeg");
			ImageIO.write(pngImage, "jpg", byteArrayOutputStream);
			File pngFile = new File(fileDir + "/image.jpg");
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

		// 1. File file = new File("d:/temp/1.bmp");
		// 2. Image img = ImageIO.read(file);
		// 3. BufferedImage tag = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_RGB);
		// 4. tag.getGraphics().drawImage(img.getScaledInstance(img.getWidth(null), img.getHeight(null), Image.SCALE_SMOOTH), 0, 0, null);
		// 5. FileOutputStream out = new FileOutputStream("d:/temp/bmp2jpg.jpg");
		// 6. // JPEGImageEncoder������������ͼƬ���͵�ת��
		// 7. JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
		// 8. encoder.encode(tag);
		// 9. out.close();
	}
}
