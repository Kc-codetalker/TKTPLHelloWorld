//
// Created by kace on 05/12/20.
//

#include <jni.h>

#include <string>

extern "C" JNIEXPORT jstring JNICALL

Java_id_ac_ui_cs_mobileprogramming_kace_helloworld_MainActivity_stringFromJNI( JNIEnv *env, jobject /* this */, jstring str) {
    jboolean isCopy;
    std::string hello = "Hello, ";
    std::string name = env->GetStringUTFChars(str, &isCopy);
    std::string fullHello = hello.append(name).append("!");
    return env->NewStringUTF(fullHello.c_str());
}