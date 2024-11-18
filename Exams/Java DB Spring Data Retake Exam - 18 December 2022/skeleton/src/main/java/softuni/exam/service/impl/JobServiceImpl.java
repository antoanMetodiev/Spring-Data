package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.xmls.JobRootDto;
import softuni.exam.models.dto.xmls.JobSeedDto;
import softuni.exam.models.entity.Job;
import softuni.exam.repository.JobRepository;
import softuni.exam.service.JobService;
import softuni.exam.util.ValidationUtilImpl;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Service
public class JobServiceImpl implements JobService {
    private final String FILE_PATH = "D:\\Programming\\Java\\Courses\\Spring Data (ORM)\\Exams\\Java DB Spring Data Retake Exam - 18 December 2022\\skeleton\\src\\main\\resources\\files\\xml\\jobs.xml";

    private final JobRepository jobRepository;
    private final ModelMapper modelMapper;
    private final ValidationUtilImpl validationUtil;

    public JobServiceImpl(JobRepository jobRepository, ModelMapper modelMapper, ValidationUtilImpl validationUtil) {
        this.jobRepository = jobRepository;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public boolean areImported() {
        return this.jobRepository.count() > 0;
    }

    @Override
    public String readJobsFileContent() throws IOException {
        return Files.readString(Path.of(FILE_PATH));
    }

    @Override
    public String importJobs() throws IOException, JAXBException {
        StringBuilder sb = new StringBuilder();

        System.out.println();
        JobRootDto jobRootDto =
                (JobRootDto) JAXBContext.newInstance(JobRootDto.class)
                        .createUnmarshaller().unmarshal(new File(FILE_PATH));


        for (JobSeedDto jobSeedDto : jobRootDto.getJobSeedDtoList()) {

//            this.jobRepository.find
            if(!this.validationUtil.isValid(jobSeedDto)) {
                sb.append("Invalid job").append(System.lineSeparator());
                continue;
            }

            Job forPers = this.modelMapper.map(jobSeedDto, Job.class);
            this.jobRepository.saveAndFlush(forPers);
            sb.append(String.format("Successfully imported job %s", jobSeedDto.getJobTitle()))
                    .append(System.lineSeparator());
        }

        return sb.toString();
    }

    @Override
    public String getBestJobs() {
        StringBuilder sb = new StringBuilder();

        System.out.println();
        List<Job> response =  this.jobRepository.findBySalaryGreaterOrEqualThanAndHoursAWeekAreLesserThan();

        for (Job job : response) {
            sb.append(String.format("Job title %s\n" +
                            "-Salary: %.2f$\n" +
                            "--Hours a week: %.2fh.\n"
                            , job.getTitle(), job.getSalary(), job.getHoursAWeek()))
                    .append(System.lineSeparator());
        }

        return sb.toString();
    }
}
