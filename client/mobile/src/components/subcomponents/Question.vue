<template>
<div class="mt-4">

  <div class="white elevation-2" style="overflow: auto;">
    <div style="width: 100%;" class="grey lighten-2 pa-2">
      <h3>Question:</h3>
    </div>
    <v-alert type="warning" :value="question.activeStatus === 'P'">This question is pending admin approval.</v-alert>
    <div class="pt-2 px-2" style="font-size: 16px;" v-html="question.question"></div>
    <div style="display: inline-block" v-if="$store.state.currentUser.username === question.createUser">
      <v-btn icon @click="openEditQuestionDialog()">   <v-icon class="icon">edit</v-icon></v-btn>
      <v-btn icon @click="deleteQuestionDialog = true"><v-icon class="icon">delete</v-icon></v-btn>
    </div>
    <v-btn icon @click="answerQuestionDialog = true"><v-icon>fas fa-reply</v-icon></v-btn>
    <v-btn :loading="loading" v-if="!showAnswers" flat small @click="getAnswers(question.questionId); showAnswers = true;">View Answers</v-btn>
    <v-btn :loading="loading" v-else-if="noAnswers" disabled flat small>No Answers</v-btn>
    <v-btn :loading="loading" v-else flat small @click="showAnswers = false">Hide Answers</v-btn>

    <transition name="slide">
    <div v-if="showAnswers">
      <Answer v-for="answer in answers" :answer="answer" :key="answer.responseId"></Answer>
    </div>
    </transition>
  </div>

  <v-dialog
    v-model="answerQuestionDialog"
    >
    <v-card>
      <v-card-title>
        <h2>Answer a Question</h2>
        <v-alert type="warning" :value="true">Do not enter any ITAR restricted, FOUO, Proprietary or otherwise sensitive information.</v-alert>
        <v-alert type="info" :value="true">All questions need admin approval before being made public.</v-alert>
      </v-card-title>
      <v-card-text>
        <div v-html="question.question"></div>
        <quill-editor
        style="background-color: white;"
        v-model="newAnswer"
        ></quill-editor>
      </v-card-text>
      <v-card-actions>
        <v-btn @click="submitAnswer(question.questionId)">Submit</v-btn>
        <v-btn @click="answerQuestionDialog = false; newAnswer = '';">Cancel</v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>

  <v-dialog
    v-model="deleteQuestionDialog"
    max-width="300px"
    >
    <v-card>
      <v-card-text>
        <p>Are you sure you want to delete this question?</p>
      </v-card-text>
      <v-card-actions>
        <v-btn color="warning" @click="deleteQuestion(answer.responseId)"><v-icon>delete</v-icon> Delete</v-btn>
        <v-btn @click="deleteQuestionDialog = false">Cancel</v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>

  <v-dialog
    v-model="editQuestionDialog"
    >
    <v-card>
      <v-card-title>
        <h2>Edit a Question</h2>
        <v-alert type="warning" :value="true">Do not enter any ITAR restricted, FOUO, Proprietary or otherwise sensitive information.</v-alert>
        <v-alert type="info" :value="true">All questions need admin approval before being made public.</v-alert>
      </v-card-title>
      <v-card-text>
        <quill-editor
        style="background-color: white;"
        v-model="newQuestion"
        ></quill-editor>
      </v-card-text>
      <v-card-actions>
        <v-btn color="success" @click="editQuestion(question.questionId)">Submit</v-btn>
        <v-btn @click="editQuestionDialog = false">Cancel</v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>

</div>
</template>

<script>
import Answer from './Answer';
import _ from 'lodash';

export default {
  name: 'Question',
  props: ['question'],
  components: {
    Answer
  },
  mounted () {
  },
  data () {
    return {
      answers: {},
      showAnswers: false,
      edit: false,
      answerQuestionDialog: false,
      deleteQuestionDialog: false,
      editQuestionDialog: false,
      newAnswer: '',
      noAnswers: false,
      newQuestion: '',
      errors: [],
      loading: false
    };
  },
  methods: {
    getAnswers (qid) {
      if (_.isEmpty(this.answers)) {
        this.loading = true;
        this.$http.get(`/openstorefront/api/v1/resource/components/${this.question.componentId}/questions/${qid}/responses`)
          .then(response => {
            this.answers = response.data;
            if (this.answers.length === 0) {
              this.noAnswers = true;
            }
            this.loading = false;
          })
          .catch(e => this.errors.push(e));
      }
    },
    submitAnswer (qid) {
      console.log(`Answer question ${qid}`);
      let data = {
        dataSensitivity: '',
        organization: this.$store.state.currentUser.organization,
        questionId: qid,
        response: this.newAnswer,
        securityMarkingType: '',
        userTypeCode: this.$store.state.currentUser.userTypeCode
      };
      this.$http.post(`/openstorefront/api/v1/resource/components/${this.question.componentId}/questions/${qid}/responses`, data)
        .then(response => {
          // answer created
          if (response.status === 201) {
            this.$toasted.show('Answer submitted.');
          }
          this.getAnswers(qid);
          this.newAnswer = '';
          this.answerQuestionDialog = false;
        })
        .catch(e => this.$toasted.error('There was a problem submitting the answer.'));
    },
    deleteQuestion (qid) {
      this.$http.delete(`http://localhost:8080/openstorefront/api/v1/resource/components/${this.question.componentId}/questions/${qid}`)
        .then(response => {
          this.deleteQuestionDialog = false;
          this.$toasted.show('Question deleted.');
          this.$emit('deleteQuestion');
        })
        .catch(e => this.$toasted.error('There was a problem deleting the answer.'));
    },
    editQuestion (qid) {
      let data = {
        dataSensitivity: '',
        organization: this.$store.state.currentUser.organization,
        question: this.newQuestion,
        securityMarkingType: '',
        userTypeCode: this.$store.state.currentUser.userTypeCode
      };
      this.$http.put(`/openstorefront/api/v1/resource/components/${this.question.componentId}/questions/${qid}`, data)
        .then(response => {
          // question submitted
          console.log('Question asked, awaiting approval');
          this.question = response.data;
          this.newQuestion = '';
          this.$toasted.show('Edited question submitted.');
          this.editQuestionDialog = false;
        })
        .catch(e => this.$toasted.error('There was a problem submitting the edit.'));
    },
    openEditQuestionDialog () {
      this.newQuestion = this.question.question;
      this.editQuestionDialog = true;
    }
  },
  computed: {
  },
  watch: {
  }
};
</script>

<style>
.icon {
  font-size: 18px;
}
.btn {
  margin: 0;
}
.slide-enter-active, .slide-leave-active {
  transition: 0.5s cubic-bezier(.25,.8,.5,1);
  max-height: 50em;
  overflow: hidden;
}
.slide-enter, .slide-leave-to {
  max-height: 0;
}
</style>