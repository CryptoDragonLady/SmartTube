package com.google.android.exoplayer2.source.sabr.parser.ump;

import com.google.android.exoplayer2.extractor.ExtractorInput;

import java.io.IOException;

public class UMPPart {
    public final int partId;
    public final int size;
    public final ExtractorInput data;

    public UMPPart(int partId, int size, ExtractorInput data) {
        this.partId = partId;
        this.size = size;
        this.data = data;
    }

    public UMPInputStream toStream() {
        return new UMPInputStream(this);
    }

    public void skip() throws IOException {
        try {
            data.skipFully(size);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }
}
