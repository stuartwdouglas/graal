/*
 * Copyright (c) 2018, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */
package org.graalvm.compiler.truffle.runtime;

import java.util.ArrayList;

import com.oracle.truffle.api.frame.Frame;

/**
 * A collection for broadcasting {@link GraalTruffleRuntimeListener} events.
 */
@SuppressWarnings("serial")
final class GraalTruffleRuntimeListenerDispatcher extends ArrayList<GraalTruffleRuntimeListener> implements GraalTruffleRuntimeListener {

    @Override
    public boolean add(GraalTruffleRuntimeListener e) {
        if (e != this && !contains(e)) {
            return super.add(e);
        }
        return false;
    }

    @Override
    public void onCompilationSplit(OptimizedDirectCallNode callNode) {
        for (GraalTruffleRuntimeListener l : this) {
            l.onCompilationSplit(callNode);
        }
    }

    @Override
    public void onCompilationQueued(OptimizedCallTarget target) {
        for (GraalTruffleRuntimeListener l : this) {
            l.onCompilationQueued(target);
        }
    }

    @Override
    public void onCompilationDequeued(OptimizedCallTarget target, Object source, CharSequence reason) {
        for (GraalTruffleRuntimeListener l : this) {
            l.onCompilationDequeued(target, source, reason);
        }
    }

    @Override
    public void onCompilationFailed(OptimizedCallTarget target, String reason, boolean bailout, boolean permanent) {
        for (GraalTruffleRuntimeListener l : this) {
            l.onCompilationFailed(target, reason, bailout, permanent);
        }
    }

    @Override
    public void onCompilationStarted(OptimizedCallTarget target) {
        for (GraalTruffleRuntimeListener l : this) {
            l.onCompilationStarted(target);
        }
    }

    @Override
    public void onCompilationTruffleTierFinished(OptimizedCallTarget target, TruffleInlining inliningDecision) {
        for (GraalTruffleRuntimeListener l : this) {
            l.onCompilationTruffleTierFinished(target, inliningDecision);
        }
    }

    @Override
    public void onCompilationSuccess(OptimizedCallTarget target, TruffleInlining inliningDecision) {
        for (GraalTruffleRuntimeListener l : this) {
            l.onCompilationSuccess(target, inliningDecision);
        }
    }

    @Override
    public void onCompilationInvalidated(OptimizedCallTarget target, Object source, CharSequence reason) {
        for (GraalTruffleRuntimeListener l : this) {
            l.onCompilationInvalidated(target, source, reason);
        }
    }

    @Override
    public void onCompilationDeoptimized(OptimizedCallTarget target, Frame frame) {
        for (GraalTruffleRuntimeListener l : this) {
            l.onCompilationDeoptimized(target, frame);
        }
    }

    @Override
    public void onShutdown() {
        for (GraalTruffleRuntimeListener l : this) {
            l.onShutdown();
        }
    }
}
