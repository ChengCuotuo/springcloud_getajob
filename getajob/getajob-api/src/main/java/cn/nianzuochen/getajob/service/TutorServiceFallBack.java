package cn.nianzuochen.getajob.service;

import cn.nianzuochen.getajob.domain.dao.Tutor;
import cn.nianzuochen.getajob.vo.request.SelectTutorsReq;
import cn.nianzuochen.getajob.vo.response.RespBean;
import feign.hystrix.FallbackFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class TutorServiceFallBack implements FallbackFactory<TutorService> {
    @Override
    public TutorService create(Throwable throwable) {
        return new TutorService() {
            @Override
            public Tutor loadTutorById(Integer id) {
                Tutor tutor = new Tutor();
                tutor.setId(id);
                tutor.setCname("延迟导致操作失败，请稍后重试！");
                return tutor;
            }

            @Override
            public RespBean loadTutorByUserId(Integer userId) {
                return RespBean.error("延迟导致操作失败，请稍后重试！");
            }

            @Override
            public RespBean loadTutors(SelectTutorsReq selectTutorsReq) {
                return RespBean.error("延迟导致操作失败，请稍后重试！");
            }

            @Override
            public RespBean addTutor(Tutor tutor) {
                return RespBean.error("延迟导致操作失败，请稍后重试！");
            }

            @Override
            public RespBean loadSimpleTutors() {
                return RespBean.error("延迟导致操作失败，请稍后重试！");
            }

            @Override
            public RespBean deleteTutors(String ids) {
                return RespBean.error("延迟导致操作失败，请稍后重试！");
            }

            @Override
            public RespBean deleteTutorById(Integer id) {
                return RespBean.error("延迟导致操作失败，请稍后重试！");
            }

            @Override
            public RespBean updateTutor(Tutor tutor) {
                return RespBean.error("延迟导致操作失败，请稍后重试！");
            }

            @Override
            public ResponseEntity<byte[]> exportTutors() {
                return null;
            }
        };
    }
}
