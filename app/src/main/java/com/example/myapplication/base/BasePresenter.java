package com.example.myapplication.base;

import com.example.myapplication.interfaces.IPresenterImpl;

import java.lang.ref.WeakReference;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BasePresenter<V extends BaseView>  implements IPresenterImpl<V> {
    protected V mView;
    CompositeDisposable compositeDisposable;
    WeakReference<V> weakReference;

    @Override
    public void attachView(V v) {
        weakReference=new WeakReference<>(v);
        this.mView=weakReference.get();
    }

    public void addDisposable(Disposable disposable){
        if (compositeDisposable!=null){
            compositeDisposable=new CompositeDisposable();
            compositeDisposable.add(disposable);
        }
    }

    public void clearDisposable(){
        if (compositeDisposable!=null&&compositeDisposable.size()>0){
            compositeDisposable.clear();
        }
    }

    @Override
    public void detachView() {
        mView=null;
        clearDisposable();
    }
}
