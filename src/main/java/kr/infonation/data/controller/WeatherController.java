package kr.infonation.data.controller;

import kr.infonation.data.dto.WeatherDto;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Spliterator;

@Controller
@RequestMapping("/weather")
public class WeatherController {

    @GetMapping("/list")
    public String weekWeatherList(Model model) throws IOException {
        String WeatherURL = "https://weather.naver.com/today/02113128";
        Document doc = Jsoup.connect(WeatherURL).get();
        Elements elem = doc.select("#weekly > div.scroll_control.end_left > div > ul > li");

        List<WeatherDto> weatherList = new ArrayList<>();

        for (Element liElement : elem) {
            WeatherDto weatherDto = new WeatherDto();

            Element dayElement = liElement.selectFirst(".day");
            Element dateElement = liElement.selectFirst(".date");
            Element weatherElement = liElement.selectFirst(".cell_weather");
            Element temperatureElement = liElement.selectFirst(".cell_temperature");

            String day = dayElement.text();
            String date = dateElement.text();
            String weather = weatherElement.text();
            String temperature = temperatureElement.text();

            String[] str = weather.split(" ");

            weather = str[0] +" " + str[1] +" / " + str[str.length-2] + " " + str[str.length-1] +" / ";
            for(int i = 2; i< str.length-2; i ++){
                weather = weather + " " + str[i];
            }

            weatherDto.setDay(day);
            weatherDto.setDate(date);
            weatherDto.setWeather(weather);
            weatherDto.setTemperature(temperature);

            weatherList.add(weatherDto);
        }

        model.addAttribute("weatherList", weatherList);

        return "weather/weather";
    }

    @GetMapping("/2")
    public Spliterator<Element> weather2() throws IOException {
        String WeatherURL = "https://weather.naver.com/today/02113128";
        Document doc = Jsoup.connect(WeatherURL).get();
        Elements elem = doc.select("#weekly > div.scroll_control.end_left");
        String[] str = elem.text().split("/");
        Spliterator<Element> spliterator = elem.stream().spliterator();
        return spliterator;
    }

    @GetMapping("/3")
    public String[] weather3() throws IOException {
        String WeatherURL = "https://weather.naver.com/today/02113128";
        Document doc = Jsoup.connect(WeatherURL).get();
        Elements elem = doc.select("#weekly > ul");
        String[] str = elem.text().split("/");

        return str;
    }
}
