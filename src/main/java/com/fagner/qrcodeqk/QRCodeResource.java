package com.fagner.qrcodeqk;

import javax.imageio.ImageIO;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

@Path("/qrcode")
public class QRCodeResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() throws WriterException, IOException {
        QRCodeWriter barcodeWriter = new QRCodeWriter();
	    BitMatrix bitMatrix = barcodeWriter.encode("One Text", BarcodeFormat.QR_CODE, 200, 200);
     
        BufferedImage originalImage = MatrixToImageWriter.toBufferedImage(bitMatrix);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write( originalImage, "jpg", baos );
        baos.flush();
        byte[] imageInByte = baos.toByteArray();
        baos.close();

        return Base64.getEncoder().encodeToString(imageInByte);
    }
}