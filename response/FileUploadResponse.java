package response;

import request.FileUploadRequest;

import java.net.ResponseCache;

public class FileUploadResponse extends Response {
    private final boolean outFileStream;

    public FileUploadResponse(boolean sendFileStream) {
        this.outFileStream = sendFileStream;
    }

    public boolean isSendFileStream() {
        return outFileStream;
    }
}
