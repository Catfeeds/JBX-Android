package com.example.zhengdong.base.APIManager;


import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * Created by zheng.dong on 2017/12/18.
 */

public class RxBus {

//    private static volatile RxBus instance;
//    private final Relay<Object> mBus;
//
//    public RxBus() {
//        this.mBus = PublishRelay.create().toSerialized();
//    }
//
//    public static RxBus getDefault() {
//        if (instance == null) {
//            synchronized (RxBus.class) {
//                if (instance == null) {
//                    instance = Holder.BUS;
//                }
//            }
//        }
//        return instance;
//    }
//    public void post(Object obj) {
//        mBus.accept(obj);
//    }
//
//    public <T> Observable<T> toObservable(Class<T> tClass) {
//        return  mBus.ofType(tClass);
//    }
//
//    public Observable<Object> toObservable() {
//        return mBus;
//    }
//
//    public boolean hasObservers() {
//        return mBus.hasObservers();
//    }
//
//    private static class Holder {
//        private static final RxBus BUS = new RxBus();
//    }

//    private volatile static RxBus mDefaultInstance;
//    private final Subject<Object> mBus;
//
//    private final Map<Class<?>, Object> mStickyEventMap;
//
//    public RxBus() {
//        mBus = PublishSubject.create().toSerialized();
//        mStickyEventMap = new ConcurrentHashMap<>();
//    }
//
//    public static RxBus getInstance() {
//        if (mDefaultInstance == null) {
//            synchronized (RxBus.class) {
//                if (mDefaultInstance == null) {
//                    mDefaultInstance = new RxBus();
//                }
//            }
//        }
//        return mDefaultInstance;
//    }
//
//    /**
//     * 发送事件
//     */
//    public void post(Object event) {
//        mBus.onNext(event);
//    }
//
//    /**
//     * 根据传递的 eventType 类型返回特定类型(eventType)的 被观察者
//     */
//    public <T> Observable<T> toObservable(final Class<T> eventType) {
//        return mBus.ofType(eventType);
//    }
//
//    /**
//     * 判断是否有订阅者
//     */
//    public boolean hasObservers() {
//        return mBus.hasObservers();
//    }
//
//    public void reset() {
//        mDefaultInstance = null;
//    }
//
//    /**
//     * Stciky 相关
//     */
//
//    /**
//     * 发送一个新Sticky事件
//     */
//    public void postSticky(Object event) {
//        synchronized (mStickyEventMap) {
//            mStickyEventMap.put(event.getClass(), event);
//        }
//        post(event);
//    }
//
//    /**
//     * 根据传递的 eventType 类型返回特定类型(eventType)的 被观察者
//     */
//    public <T> Observable<T> toObservableSticky(final Class<T> eventType) {
//        synchronized (mStickyEventMap) {
//            Observable<T> observable = mBus.ofType(eventType);
//            final Object event = mStickyEventMap.get(eventType);
//
//            if (event != null) {
//                return observable.mergeWith(Observable.create(new ObservableOnSubscribe<T>() {
//                    @Override
//                    public void subscribe(ObservableEmitter<T> subscriber) throws Exception {
//                        subscriber.onNext(eventType.cast(event));
//                    }
//                }));
//            } else {
//                return observable;
//            }
//        }
//    }
//
//    /**
//     * 根据eventType获取Sticky事件
//     */
//    public <T> T getStickyEvent(Class<T> eventType) {
//        synchronized (mStickyEventMap) {
//            return eventType.cast(mStickyEventMap.get(eventType));
//        }
//    }
//
//    /**
//     * 移除指定eventType的Sticky事件
//     */
//    public <T> T removeStickyEvent(Class<T> eventType) {
//        synchronized (mStickyEventMap) {
//            return eventType.cast(mStickyEventMap.remove(eventType));
//        }
//    }
//
//    /**
//     * 移除所有的Sticky事件
//     */
//    public void removeAllStickyEvents() {
//        synchronized (mStickyEventMap) {
//            mStickyEventMap.clear();
//        }
//    }
//
//
//    /**
//     * 普通订阅解绑
//     * @param disposable
//     */
//    public static   void  rxBusUnbund(CompositeDisposable disposable){
//        if (null != disposable && !disposable.isDisposed()) {
//            disposable.clear();
//        }
//    }


//    private static volatile RxBus mInstance;
//    private final Subject<Object> subject = PublishSubject.create().toSerialized();
//    private Disposable dispoable;
//
//
//    private RxBus() {
//    }
//
//    public static RxBus getInstance() {
//        if (mInstance == null) {
//            synchronized (RxBus.class) {
//                if (mInstance == null) {
//                    mInstance = new RxBus();
//                }
//            }
//        }
//        return mInstance;
//    }
//
//
//    /**
//     * 发送事件
//     * @param object
//     */
//    public void send(Object object) {
//        subject.onNext(object);
//    }
//
//
//    /**
//     * @param classType
//     * @param <T>
//     * @return
//     */
//    public <T> Observable<T> toObservale(Class<T> classType) {
//        return subject.ofType(classType);
//    }
//
//
//    /**
//     * 订阅
//     * @param bean
//     * @param consumer
//     */
//    public void subscribe(Class bean, Consumer consumer) {
//        dispoable = toObservale(bean).subscribe(consumer);
//    }
//
//    /**
//     * 取消订阅
//     */
//    public void unSubcribe(){
//        if (dispoable != null && dispoable.isDisposed()){
//            dispoable.dispose();
//        }
//
//    }

    private static volatile RxBus defaultInstance;

    private final Subject<Object, Object> bus;
    // PublishSubject只会把在订阅发生的时间点之后来自原始Observable的数据发射给观察者
    public RxBus() {
        bus = new SerializedSubject<>(PublishSubject.create());
    }
    // 单例RxBus
    public static RxBus getDefault() {
        if (defaultInstance == null) {
            synchronized (RxBus.class) {
                if (defaultInstance == null) {
                    defaultInstance = new RxBus();
                }
            }
        }
        return defaultInstance ;
    }
    // 发送一个新的事件
    public void post (Object o) {
        bus.onNext(o);
    }
    // 根据传递的 eventType 类型返回特定类型(eventType)的 被观察者
    public <T> Observable<T> toObservable (Class<T> eventType) {
        return bus.ofType(eventType);
//        这里感谢小鄧子的提醒: ofType = filter + cast
//        return bus.filter(new Func1<Object, Boolean>() {
//            @Override
//            public Boolean call(Object o) {
//                return eventType.isInstance(o);
//            }
//        }) .cast(eventType);
    }
}
