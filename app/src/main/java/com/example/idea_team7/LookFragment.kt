package com.example.idea_team7

import android.content.Context.MODE_PRIVATE
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.idea_team7.databinding.FragmentLookBinding
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView
import org.json.JSONArray

class LookFragment : Fragment() {
    private lateinit var binding: FragmentLookBinding
    private var youTubePlayer: YouTubePlayer? = null
    private val handler = Handler(Looper.getMainLooper())
    private val captionsOriginal = """
        [
            {"text": "1963년의 뜨거운 봄날 오후,", "start": 7.003, "duration": 3.17},
            {"text": "미국 CIA가 보낸 두 남자가", "start": 10.173, "duration": 2.919},
            {"text": "하바나 리브레 호텔의 구내식당에 몰래 들어갔습니다.", "start": 13.092, "duration": 4.088},
            {"text": "그들의 임무는 냉동고에서 독약 알약을 꺼내", "start": 17.43, "duration": 3.211},
            {"text": "피델 카스트로의 초콜릿 밀크셰이크에 넣는 것이었습니다.", "start": 20.641, "duration": 3.295},
            {"text": "점심 후 최대 18스쿱의 아이스크림을 먹는 것으로 유명했던 쿠바의 지도자 피델 카스트로의.", "start": 23.978, "duration": 4.963},
            {"text": "이 이야기의 정확한 세부 사항은 논란이 있지만, 알약이 냉동고에 달라붙어 부서졌다는 소문이 있습니다.", "start": 29.233, "duration": 4.672},
            {"text": "CIA의 계획은 실패했고, 카스트로는 그의 단 맛을 더 오랫동안 즐길 수 있게 되었습니다.", "start": 33.905, "duration": 3.295},
            {"text": "아이스크림은 우리의 역사, 문화, 그리고 갈망 속에서 독특한 역할을 해왔습니다—", "start": 37.283, "duration": 5.881},
            {"text": "그러나 그 시작은 어디서부터였을까요?", "start": 43.456, "duration": 1.751},
            {"text": "차가운 디저트와 얼음 음료의 첫 기록은", "start": 51.214, "duration": 3.545},
            {"text": "1세기까지 거슬러 올라갑니다.", "start": 54.759, "duration": 2.085},
            {"text": "고대 로마, 무굴 제국 인도 그리고 당나라 중국을 포함한 문명에서,", "start": 57.011, "duration": 5.589},
            {"text": "이 냉동 간식들은 주로 왕족 엘리트들이 즐겼습니다.", "start": 62.6, "duration": 4.087},
            {"text": "그리고 이 진미를 얼리기 위한 수단을 찾는 것은 항상 쉬운 일이 아니었습니다.", "start": 66.771, "duration": 4.171},
            {"text": "부유한 지중해 귀족들은 노동자들을 보내 높은 산으로 보내", "start": 71.109, "duration": 4.587},
            {"text": "빙하의 얼음과 눈을 수확하게 했습니다.", "start": 75.696, "duration": 2.211},
            {"text": "한편, 고대 페르시아인들은 얕은 절연 물 웅덩이를 만들고,", "start": 77.99, "duration": 3.963},
            {"text": "하늘 냉각이라는 기술을 활용하였습니다.", "start": 81.953, "duration": 3.17},
            {"text": "밤에는 얕은 웅덩이가 자연스럽게 열을 방출하여,", "start": 85.206, "duration": 3.378},
            {"text": "건조한 사막 하늘로 방출하여,", "start": 88.584, "duration": 2.086},
            {"text": "해당 웅덩이들이 주변 온도보다 낮아져 얼게 만듭니다.", "start": 90.753, "duration": 4.213},
            {"text": "그러나 우리가 알고 있는 크림 베이스의 간식은 훨씬 나중에 등장했습니다.", "start": 95.174, "duration": 4.254},
            {"text": "그것은 셔벗, 또는 아랍어로 샤르바트에서 영감을 받았습니다.", "start": 99.595, "duration": 4.63},
            {"text": "페르시아에서 유래한 것으로 추정되는 얼음 음료로,", "start": 104.225, "duration": 3.42},
            {"text": "중세 시기에 인기를 끌었습니다.", "start": 107.645, "duration": 3.504},
            {"text": "유럽 여행자들이 샤르바트 레시피를 자신의 나라로 가져와,", "start": 111.315, "duration": 3.504},
            {"text": "초콜릿, 솔방울,", "start": 114.819, "duration": 3.962},
            {"text": "심지어 가지 맛까지 다양한 해석을 추가했습니다.", "start": 118.781, "duration": 3.128},
            {"text": "1692년, 나폴리의 요리사 안토니오 라티니는", "start": 122.034, "duration": 4.922},
            {"text": "독특한 우유 기반 버전의 레시피를 기록했는데,", "start": 126.956, "duration": 3.545},
            {"text": "일부 역사가들은 이를 최초의 아이스크림이라고 부릅니다.", "start": 130.501, "duration": 3.337},
            {"text": "18세기에 들어서, 아이스크림은 그 범위를 넓혔고,", "start": 134.046, "duration": 3.045},
            {"text": "이 레시피들은 유럽 정착민들과 함께 북미로 건너갔습니다.", "start": 137.091, "duration": 5.38},
            {"text": "그러나 여전히 주요 소비자는 상류층이었으며,", "start": 142.471, "duration": 3.421},
            {"text": "제작 과정이 꽤 힘들었기 때문입니다.", "start": 145.892, "duration": 2.877},
            {"text": "그리고 주요 재료— 설탕, 소금, 크림— 이 비쌌기 때문입니다.", "start": 148.769, "duration": 4.88},
            {"text": "조지 워싱턴은 한 여름에 아이스크림에만 오늘날 가치로 약 ${'$'}6,600을 썼다고 합니다.", "start": 153.941, "duration": 4.88},
            {"text": "미국에서는 이 냉동 디저트가 황금 시대를 맞이했으며,", "start": 158.821, "duration": 4.213},
            {"text": "발명가와 기업가들이 대중에게 이를 제공할 방법을 고안하기 시작했습니다.", "start": 163.117, "duration": 4.588},
            {"text": "1843년 필라델피아에서, 낸시 존슨은", "start": 167.705, "duration": 5.172},
            {"text": "크랭크와 비터가 있는 혁신적인 아이스크림 제조기를 특허를 냈습니다.", "start": 172.96, "duration": 4.63},
            {"text": "이는 어떤 가정 요리사라도 쉽게 만들 수 있게 해주었습니다.", "start": 177.59, "duration": 4.546},
            {"text": "그리고 1830년대 중반까지는 아이스크림 저장도 문제가 되지 않았습니다.", "start": 182.136, "duration": 3.42},
            {"text": "뉴잉글랜드 사업가 '얼음 왕' 프레데릭 튜더는", "start": 185.681, "duration": 4.88},
            {"text": "얼음 무역을 크게 개선하여,", "start": 190.561, "duration": 3.879},
            {"text": "전 세계 가정으로 수천 톤의 얼음을 운송했습니다.", "start": 194.44, "duration": 2.544},
            {"text": "곧 아이스크림은 거리 곳곳에서 팔리게 되었습니다.", "start": 196.984, "duration": 4.63},
            {"text": "1880년대 후반, 정치적 혼란으로 이탈리아 이민자들이 런던, 글래스고, 뉴욕 같은 도시로 이주하게 되었습니다.", "start": 201.739, "duration": 3.42},
            {"text": "그들은 많은 수가 거리에서 아이스크림을 판매하는 일을 했습니다.", "start": 205.284, "duration": 4.505},
            {"text": "회사당 한 푼 씩 받았습니다.", "start": 209.789, "duration": 2.419},
            {"text": "한편, 미국 약사들은 소다와 아이스크림을 결합하여 새로운 사회적 장소인 소다 분수를 탄생시켰습니다.", "start": 212.208, "duration": 4.504},
            {"text": "소다는 당시 치료 효과가 있는 것으로 여겨졌습니다.", "start": 218.923, "duration": 5.297},
            {"text": "1920년 알코올 판매가 금지되자, 많은 미국 식당은 소다 분수가 되었습니다.", "start": 224.22, "duration": 3.17},
            {"text": "브루어리들은 아이스크림 생산으로 전환했습니다.", "start": 227.39, "duration": 1.251},
            {"text": "동시에 냉동 기술이 급속도로 발전했습니다.", "start": 232.478, "duration": 3.17},
            {"text": "제2차 세계 대전이 끝날 무렵, 일반 미국 가정의 냉동고에는 1쿼트의 아이스크림을 보관할 수 있었습니다.", "start": 235.648, "duration": 4.379},
            {"text": "트럭조차도 냉동간식을 가득 싣고 다닐 수 있었습니다.", "start": 240.027, "duration": 2.962},
            {"text": "오늘날, 아이스크림은 새로운 형태로 계속 진화하고 있습니다.", "start": 242.989, "duration": 2.502},
            {"text": "그 미스터리 중 일부는 결코 풀리지 않을 수도 있지만, 한 가지는 확실합니다: 아이스크림에 대한 우리의 사랑은 절대 녹지 않을 것입니다.", "start": 245.992, "duration": 4.212}
        ]
    """
    private val captionsFriend = """
        [
            {"text": "1963년 어느 따뜻한 봄날 오후,", "start": 7.003, "duration": 3.17},
            {"text": "미국 CIA에서 보낸 두 남자가,", "start": 10.173, "duration": 2.919},
            {"text": "하바나 리브레 호텔 식당에 몰래 들어갔어.", "start": 13.092, "duration": 4.088},
            {"text": "그들의 임무는 냉동고에서\n독약 캡슐을 꺼내", "start": 17.43, "duration": 3.211},
            {"text": "카스트로의 초콜릿 밀크셰이크에\n넣는 것이었지.", "start": 20.641, "duration": 3.295},
            {"text": "카스트로는 점심 후 아이스크림을\n최대 18스쿱까지 먹는 것으로 유명했어.", "start": 23.978, "duration": 4.963},
            {"text": "하지만 이야기의 정확한 내용은 논란이 되고 있지만,\n캡슐이 냉동고 코일에 달라붙어 부서졌다는 소문이 있어.", "start": 29.233, "duration": 4.672},
            {"text": "이로 인해 CIA의 계획이 실패했고,\n카스트로는 더 많은 날들을 살게 되었어.", "start": 33.905, "duration": 3.295},
            {"text": "아이스크림은 우리 세계의 역사, 문화, 그리고\n갈망에서 독특한 역할을 해왔어—", "start": 37.283, "duration": 5.881},
            {"text": "그렇다면 아이스크림은 어디서 유래했을까?", "start": 43.456, "duration": 1.751},
            {"text": "차가운 디저트와 냉음료의 최초 기록은", "start": 51.214, "duration": 3.545},
            {"text": "1세기 초로 거슬러 올라가.", "start": 54.759, "duration": 2.085},
            {"text": "고대 로마, 무굴 인도, 당나라 중국을 포함한 문명에서,", "start": 57.011, "duration": 5.589},
            {"text": "이런 얼음 간식은 주로 왕족들이 즐겼어.", "start": 62.6, "duration": 4.087},
            {"text": "이러한 간식을 냉동시키는 방법을 찾는 것은\n항상 쉬운 일이 아니었어.", "start": 66.771, "duration": 4.171},
            {"text": "부유한 지중해 귀족들은 노동자들을 높은 산으로 보내", "start": 71.109, "duration": 4.587},
            {"text": "빙하의 얼음과 눈을 수확하게 했어.", "start": 75.696, "duration": 2.211},
            {"text": "한편, 고대 페르시아인들은 얕고 단열된 물 웅덩이를 만들고", "start": 77.99, "duration": 3.963},
            {"text": "‘하늘 냉각’이라는 기술을 사용했어.", "start": 81.953, "duration": 3.17},
            {"text": "밤이 되면 이 얕은 웅덩이는 자연적으로 열을", "start": 85.206, "duration": 3.378},
            {"text": "건조한 사막 하늘로 방출해서,", "start": 88.584, "duration": 2.086},
            {"text": "주변 온도보다 낮아져서 얼게 만들어줬어.", "start": 90.753, "duration": 4.213},
            {"text": "그러나 우리가 오늘날 알고 있는 크림 베이스의 간식은 훨씬 후에 등장했어.", "start": 95.174, "duration": 4.254},
            {"text": "그것은 원래 셔벗, 혹은 아랍어로 샤르바트에서 영감을 받았어,", "start": 99.595, "duration": 4.63},
            {"text": "이 차가운 음료는 페르시아에서 유래한 것으로 믿어지고 있어,", "start": 104.225, "duration": 3.42},
            {"text": "그 후 중세에 인기를 얻었지.", "start": 107.645, "duration": 3.504},
            {"text": "유럽 여행자들이 샤르바트 레시피를 가져와서,", "start": 111.315, "duration": 3.504},
            {"text": "자신들만의 초콜릿, 솔방울,", "start": 114.819, "duration": 3.962},
            {"text": "그리고 심지어 가지 맛까지 추가해서 만들기 시작했어.", "start": 118.781, "duration": 3.128},
            {"text": "1692년, 나폴리 셰프 안토니오 라티니가,", "start": 122.034, "duration": 4.922},
            {"text": "독특한 밀크 베이스 버전의 레시피를 기록했어,", "start": 126.956, "duration": 3.545},
            {"text": "역사학자들은 이것을 최초의 아이스크림으로 불러.", "start": 130.501, "duration": 3.337},
            {"text": "18세기에 아이스크림은 그 영역을 확장했어", "start": 134.046, "duration": 3.045},
            {"text": "유럽 정착민들과 함께 북미로 항해를 떠나면서.", "start": 137.091, "duration": 5.38},
            {"text": "하지만 여전히 주로 상류층이 즐겼었어", "start": 142.471, "duration": 3.421},
            {"text": "그 이유는 만들기 위해선 많은 노력이 필요했고,", "start": 145.892, "duration": 2.877},
            {"text": "주요 재료인 설탕, 소금, 크림이 비쌌기 때문이야.", "start": 148.769, "duration": 4.88},
            {"text": "조지 워싱턴이 여름 한 철 동안 아이스크림에만\n6600달러 상당의 돈을 썼다고 해.", "start": 153.941, "duration": 4.88},
            {"text": "미국 땅에서 이 얼음 디저트는 황금기를 맞았어,", "start": 158.821, "duration": 4.213},
            {"text": "발명가들과 기업가들이 대중에게 제공할\n방법을 고안하기 시작하면서.", "start": 163.117, "duration": 4.588},
            {"text": "1843년 필라델피아에서, 낸시 존슨은", "start": 167.705, "duration": 4.63},
            {"text": "손잡이와 비터가 있는 혁신적인 아이스크림 제조기를 특허 받았어,", "start": 172.96, "duration": 4.546},
            {"text": "이로 인해 모든 가정 요리사들이 아이스크림을 쉽게 만들 수 있게 되었지.", "start": 177.59, "duration": 3.42},
            {"text": "그리고 아이스크림을 저장하는 것도 더 이상 문제가 아니었어, 1830년대 중반까지는,", "start": 182.136, "duration": 4.88},
            {"text": "뉴잉글랜드 사업가 '아이스 킹' 프레더릭 튜더가", "start": 190.561, "duration": 3.879},
            {"text": "얼음 무역을 크게 개선했거든,", "start": 194.44, "duration": 2.544},
            {"text": "전 세계 가정에 수천 톤의 얼음을 운송하면서.", "start": 196.984, "duration": 4.63},
            {"text": "곧 아이스크림은 모든 거리 구석구석에서 팔리기 시작했어.", "start": 201.739, "duration": 3.42},
            {"text": "1880년대 후반, 정치적 혼란으로 인해\n이탈리아 이민자들이 런던, 글래스고, 뉴욕으로 이주했어,", "start": 205.284, "duration": 4.505},
            {"text": "많은 사람들이 거리에서 아이스크림을 팔았어,", "start": 209.789, "duration": 2.419},
            {"text": "한 입 한 입에 대략 한 푼이었지.", "start": 212.208, "duration": 4.504},
            {"text": "한편, 미국 약사들은 탄산음료와 아이스크림을 결합한 것의 매력을 발견했어,", "start": 218.923, "duration": 5.297},
            {"text": "이 음료는 당시 치료 효과가 있다고 여겨졌어,", "start": 224.22, "duration": 3.17},
            {"text": "그리고 새로운 사회적 장소가 탄생했어: 소다 분수대.", "start": 227.39, "duration": 3.712},
            {"text": "1920년에 알코올 판매가 금지되었을 때,", "start": 232.478, "duration": 3.17},
            {"text": "많은 미국의 술집들이 소다 분수대로 재탄생했어,", "start": 235.648, "duration": 4.379},
            {"text": "그리고 앤호이저-부시와 윙링 같은 양조장은", "start": 240.027, "duration": 2.962},
            {"text": "아이스크림 생산으로 전환했어.", "start": 242.989, "duration": 2.502},
            {"text": "동시에, 냉장 기술은 빠르게 발전하고 있었어.", "start": 245.992, "duration": 4.212},
            {"text": "제2차 세계 대전이 끝날 즈음,", "start": 250.288, "duration": 1.543},
            {"text": "평균적인 미국 가정에는 아이스크림 한 쿼트를 보관할 수 있는 냉동고가 있었어.", "start": 251.831, "duration": 4.588},
            {"text": "심지어 트럭도 얼음 간식을 가득 채운 냉동고를 갖출 수 있게 되었어.", "start": 256.627, "duration": 3.921},
            {"text": "오늘날, 아이스크림은 계속해서 새로운 형태로 변화하고 있어.", "start": 261.048, "duration": 3.587},
            {"text": "그리고 그 비밀 중 일부는 절대 풀리지 않을지도 모르지만,", "start": 264.635, "duration": 2.961},
            {"text": "한 가지는 확실해: 우리 아이스크림에 대한 사랑은 절대 식지 않을 거야.", "start": 267.68, "duration": 5.13}
        ]
    """
    private val captionsExport = """
        [
    {"text": "1963년의 뜨거운 봄날 오후,","start": 7.003,"duration": 3.17},
    {"text": "미국 CIA가 보낸 두 남자가","start": 10.173,"duration": 2.919},
    {"text": "하바나 리브레 호텔의 구내식당에 몰래 들어갔수다.","start": 13.092,"duration": 4.088},
    {"text": "그들의 임무는 냉동고에서 독약 알약을 꺼내","start": 17.43,"duration": 3.211},
    {"text": "피델 카스트로의 초콜릿 밀크셰이크에 넣는 것이우다.","start": 20.641,"duration": 3.295},
    {"text": "점심 후 최대 18스쿱의 아이스크림을 먹는 것으로 유명했던 쿠바의 지도자 피델 카스트로의.","start": 23.978,"duration": 4.963},
    {"text": "이 이야기의 정확한 세부 사항은 논란이 있지만, 알약이 냉동고에 달라붙어 부서졌다는 소문이 있수다.","start": 29.233,"duration": 4.672},
    {"text": "CIA의 계획은 실패했고, 카스트로는 그의 단 맛을 더 오랫동안 즐길 수 있게 됐수다.","start": 33.905,"duration": 3.295},
    {"text": "아이스크림은 우리의 역사, 문화, 그리고 갈망 속에서 독특한 역할을 해왔수다—","start": 37.283,"duration": 5.881},
    {"text": "그러나 그 시작은 어디서부터였을까요?","start": 43.456,"duration": 1.751},
    {"text": "차가운 디저트와 얼음 음료의 첫 기록은","start": 51.214,"duration": 3.545},
    {"text": "1세기까지 거슬러 올라갑수다.","start": 54.759,"duration": 2.085},
    {"text": "고대 로마, 무굴 제국 인도 그리고 당나라 중국을 포함한 문명에서,","start": 57.011,"duration": 5.589},
    {"text": "이 냉동 간식들은 주로 왕족 엘리트들이 즐겼수다.","start": 62.6,"duration": 4.087},
    {"text": "그리고 이 진미를 얼리기 위한 수단을 찾는 것은 항상 쉬운 일이 아니었수다.","start": 66.771,"duration": 4.171},
    {"text": "부유한 지중해 귀족들은 노동자들을 보내 높은 산으로 보내","start": 71.109,"duration": 4.587},
    {"text": "빙하의 얼음과 눈을 수확하게 했수다.","start": 75.696,"duration": 2.211},
    {"text": "한편, 고대 페르시아인들은 얕은 절연 물 웅덩이를 만들고,","start": 77.99,"duration": 3.963},
    {"text": "하늘 냉각이라는 기술을 활용했수다.","start": 81.953,"duration": 3.17},
    {"text": "밤에는 얕은 웅덩이가 자연스럽게 열을 방출하여,","start": 85.206,"duration": 3.378},
    {"text": "건조한 사막 하늘로 방출하여,","start": 88.584,"duration": 2.086},
    {"text": "해당 웅덩이들이 주변 온도보다 낮아져 얼게 만듭수다.","start": 90.753,"duration": 4.213},
    {"text": "그러나 우리가 알고 있는 크림 베이스의 간식은 훨씬 나중에 등장했수다.","start": 95.174,"duration": 4.254},
    {"text": "그것은 셔벗, 또는 아랍어로 샤르바트에서 영감을 받았수다.","start": 99.595,"duration": 4.63},
    {"text": "페르시아에서 유래한 것으로 추정되는 얼음 음료로,","start": 104.225,"duration": 3.42},
    {"text": "중세 시기에 인기를 끌었수다.","start": 107.645,"duration": 3.504},
    {"text": "유럽 여행자들이 샤르바트 레시피를 자신의 나라로 가져와,","start": 111.315,"duration": 3.504},
    {"text": "초콜릿, 솔방울,","start": 114.819,"duration": 3.962},
    {"text": "심지어 가지 맛까지 다양한 해석을 추가했수다.","start": 118.781,"duration": 3.128},
    {"text": "1692년, 나폴리의 요리사 안토니오 라티니는","start": 122.034,"duration": 4.922},
    {"text": "독특한 우유 기반 버전의 레시피를 기록했는데,","start": 126.956,"duration": 3.545},
    {"text": "일부 역사가들은 이를 최초의 아이스크림이라고 부릅수다.","start": 130.501,"duration": 3.337},
    {"text": "18세기에 들어서, 아이스크림은 그 범위를 넓혔고,","start": 134.046,"duration": 3.045},
    {"text": "이 레시피들은 유럽 정착민들과 함께 북미로 건너갔수다.","start": 137.091,"duration": 5.38},
    {"text": "그러나 여전히 주요 소비자는 상류층이었으며,","start": 142.471,"duration": 3.421},
    {"text": "제작 과정이 꽤 힘들었기 때문이우다.","start": 145.892,"duration": 2.877},
    {"text": "그리고 주요 재료— 설탕, 소금, 크림— 이 비쌌기 때문이우다.","start": 148.769,"duration": 4.88},
    {"text": "조지 워싱턴은 한 여름에 아이스크림에만 오늘날 가치로 약 ${'$'}6,600을 썼다고 합니우다.","start": 153.941,"duration": 4.88},
    {"text": "미국에서는 이 냉동 디저트가 황금 시대를 맞이했으며,","start": 158.821,"duration": 4.213},
    {"text": "발명가와 기업가들이 대중에게 이를 제공할 방법을 고안하기 시작했수다.","start": 163.117,"duration": 4.588},
    {"text": "1843년 필라델피아에서, 낸시 존슨은","start": 167.705,"duration": 5.172},
    {"text": "크랭크와 비터가 있는 혁신적인 아이스크림 제조기를 특허를 냈수다.","start": 172.96,"duration": 4.63},
    {"text": "이는 어떤 가정 요리사라도 쉽게 만들 수 있게 해주었수다.","start": 177.59,"duration": 4.546},
    {"text": "그리고 1830년대 중반까지는 아이스크림 저장도 문제가 되지 않았수다.","start": 182.136,"duration": 3.42},
    {"text": "뉴잉글랜드 사업가 '얼음 왕' 프레데릭 튜더는","start": 185.681,"duration": 4.88},
    {"text": "얼음 무역을 크게 개선하여,","start": 190.561,"duration": 3.879},
    {"text": "전 세계 가정으로 수천 톤의 얼음을 운송했수다.","start": 194.44,"duration": 2.544},
    {"text": "곧 아이스크림은 거리 곳곳에서 팔리게 되었수다.","start": 196.984,"duration": 4.63},
    {"text": "1880년대 후반, 정치적 혼란으로 이탈리아 이민자들이 런던, 글래스고, 뉴욕 같은 도시로 이주하게 되었수다.","start": 201.739,"duration": 3.42},
    {"text": "그들은 많은 수가 거리에서 아이스크림을 판매하는 일을 했수다.","start": 205.284,"duration": 4.505},
    {"text": "회사당 한 푼 씩 받았수다.","start": 209.789,"duration": 2.419},
    {"text": "한편, 미국 약사들은 소다와 아이스크림을 결합하여 새로운 사회적 장소인 소다 분수를 탄생시켰수다.","start": 212.208,"duration": 4.504},
    {"text": "소다는 당시 치료 효과가 있는 것으로 여겨졌수다.","start": 218.923,"duration": 5.297},
    {"text": "1920년 알코올 판매가 금지되자, 많은 미국 식당은 소다 분수가 되었수다.","start": 224.22,"duration": 3.17},
    {"text": "브루어리들은 아이스크림 생산으로 전환했수다.","start": 227.39,"duration": 1.251},
    {"text": "동시에 냉동 기술이 급속도로 발전했수다.","start": 232.478,"duration": 3.17},
    {"text": "제2차 세계 대전이 끝날 무렵, 일반 미국 가정의 냉동고에는 1쿼트의 아이스크림을 보관할 수 있수다.","start": 235.648,"duration": 4.379},
    {"text": "트럭조차도 냉동간식을 가득 싣고 다닐 수 있수다.","start": 240.027,"duration": 2.962},
    {"text": "오늘날, 아이스크림은 새로운 형태로 계속 진화하고 있수다.","start": 242.989,"duration": 2.502},
    {"text": "그 미스터리 중 일부는 결코 풀리지 않을 수도 있지만, 한 가지는 확실합니우다: 아이스크림에 대한 우리의 사랑은 절대 녹지 않을 것입니우다.","start": 245.992,"duration": 4.212}
]
    """
    private var currentCaptions = captionsOriginal
    private val captionHandlers = mutableListOf<Handler>()
    private var currentCaptionIndex = -1
    private var playbackPosition = 0f
    private var isPlaying = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLookBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showVideo()
        clickListener()
        setupButtonListeners()
        selectButton(binding.originalIv, binding.originalTv) // 기본 상태 설정
    }

    private fun clickListener() {
        binding.backBtn.setOnClickListener {
            (context as MainActivity).supportFragmentManager.beginTransaction()
                .replace(R.id.activity_main_fragment_container, HomeFragment())
                .commitAllowingStateLoss()
        }
        binding.playBtn.setOnClickListener {
            if (!isPlaying) {
                youTubePlayer?.play()
                startCaptions(playbackPosition)
                isPlaying = true
            }
        }
        binding.pauseBtn.setOnClickListener {
            if (isPlaying) {
                youTubePlayer?.pause()
                stopCaptions()
                isPlaying = false
            }
        }
    }

    private fun showVideo() {
        val adsSpf = requireActivity().getSharedPreferences("Address", MODE_PRIVATE)
        val id = adsSpf.getString("id", "")

        val youTubePlayerView: YouTubePlayerView = binding.youtubePlayerView
        lifecycle.addObserver(youTubePlayerView)
        youTubePlayerView.addYouTubePlayerListener(object :
            AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                super.onReady(youTubePlayer)
                this@LookFragment.youTubePlayer = youTubePlayer
                val videoId = id
                youTubePlayer.cueVideo(videoId!!, 0f)
            }

            override fun onCurrentSecond(youTubePlayer: YouTubePlayer, second: Float) {
                super.onCurrentSecond(youTubePlayer, second)
                if (isPlaying) {
                    playbackPosition = second
                }
            }
        })
    }

    private fun startCaptions(startPosition: Float) {
        stopCaptions()
        currentCaptionIndex = -1
        val captionsArray = JSONArray(currentCaptions)
        for (i in 0 until captionsArray.length()) {
            val captionObject = captionsArray.getJSONObject(i)
            val text = captionObject.getString("text")
            val start = captionObject.getDouble("start").toFloat()
            val duration = captionObject.getDouble("duration").toFloat()

            if (start >= startPosition) {
                val startHandler = Handler(Looper.getMainLooper())
                startHandler.postDelayed({
                    currentCaptionIndex = i
                    updateCaptions(captionsArray)
                    val endHandler = Handler(Looper.getMainLooper())
                    endHandler.postDelayed({
                        if (currentCaptionIndex == i) {
                            binding.nowCaptionTv.alpha = 0.2f
                            binding.backCaptionTv.alpha = 0.5f
                            binding.nextCaptionTv.alpha = 0.2f
                        }
                    }, (duration * 1000).toLong())
                    captionHandlers.add(endHandler)
                }, ((start - startPosition) * 1000).toLong())
                captionHandlers.add(startHandler)
            }
        }
    }

    private fun updateCaptions(captionsArray: JSONArray) {
        val currentText =
            if (currentCaptionIndex >= 0) captionsArray.getJSONObject(currentCaptionIndex)
                .getString("text") else ""
        val previousText =
            if (currentCaptionIndex > 0) captionsArray.getJSONObject(currentCaptionIndex - 1)
                .getString("text") else ""
        val nextText =
            if (currentCaptionIndex < captionsArray.length() - 1) captionsArray.getJSONObject(
                currentCaptionIndex + 1
            ).getString("text") else ""

        binding.backCaptionTv.text = previousText
        binding.nowCaptionTv.text = currentText
        binding.nextCaptionTv.text = nextText

        binding.backCaptionTv.visibility =
            if (previousText.isEmpty()) View.INVISIBLE else View.VISIBLE
        binding.nowCaptionTv.visibility = if (currentText.isEmpty()) View.GONE else View.VISIBLE
        binding.nextCaptionTv.visibility = if (nextText.isEmpty()) View.GONE else View.VISIBLE

        binding.backCaptionTv.alpha = 0.3f
        binding.nowCaptionTv.alpha = 1.0f
        binding.nextCaptionTv.alpha = 0.3f
    }

    private fun stopCaptions() {
        captionHandlers.forEach { it.removeCallbacksAndMessages(null) }
        captionHandlers.clear()
        binding.backCaptionTv.alpha = 0.2f
        binding.nowCaptionTv.alpha = 0.5f
        binding.nextCaptionTv.alpha = 0.2f
    }

    private fun setupButtonListeners() {
        binding.originalIv.setOnClickListener {
            currentCaptions = captionsOriginal
            selectButton(binding.originalIv, binding.originalTv)
            restartVideoAndCaptions()
        }
        binding.friendIv.setOnClickListener {
            currentCaptions = captionsFriend
            selectButton(binding.friendIv, binding.friendTv)
            restartVideoAndCaptions()
        }
        binding.exportIv.setOnClickListener {
            currentCaptions = captionsExport
            selectButton(binding.exportIv, binding.exportTv)
            restartVideoAndCaptions()
        }
    }

    private fun selectButton(selectedImageView: ImageView, selectedTextView: TextView) {
        val imageViews = listOf(binding.originalIv, binding.friendIv, binding.exportIv)
        val textViews = listOf(binding.originalTv, binding.friendTv, binding.exportTv)

        imageViews.forEachIndexed { index, imageView ->
            if (imageView == selectedImageView) {
                imageView.setBackgroundResource(R.drawable.rectangle_corner_10)
                imageView.backgroundTintList =
                    resources.getColorStateList(R.color.selected_button_background)
                textViews[index].setTextColor(resources.getColor(R.color.white))
            } else {
                imageView.setBackgroundResource(R.drawable.rectangle_corner_10)
                imageView.backgroundTintList =
                    resources.getColorStateList(R.color.unselected_button_background)
                textViews[index].setTextColor(resources.getColor(R.color.unselected_button_text))
            }
        }
    }

    private fun restartVideoAndCaptions() {
        youTubePlayer?.seekTo(0f)
        playbackPosition = 0f
        startCaptions(playbackPosition)
        youTubePlayer?.play()
        isPlaying = true
    }
}
